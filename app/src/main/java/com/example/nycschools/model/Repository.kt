package com.example.nycschools.model

import com.example.nycschools.model.local.NYCDao
import com.example.nycschools.model.local.SatEntity
import com.example.nycschools.model.local.SchoolEntity
import com.example.nycschools.model.local.SchoolSatEntity
import com.example.nycschools.model.remote.NYCSatScore
import com.example.nycschools.model.remote.NYCSchool
import com.example.nycschools.model.remote.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class Repository(
    private val nycDao: NYCDao,
    private val network: Network,
    private val scope: CoroutineScope
) {

    // fetch school list
    // fetch school sat
    // merge both list
    // insert school entity
    // insert school sat
    // insert the merge
    private val merge = mutableListOf<SchoolSatEntity>()

    /**
     * Coroutine builders
     * CoroutineScope.launch.- Will return a JOB, the task that
     * will be executed. Similar to "runnable".
     * Example: Network call (independent), DB Transaction,
     * upload data to the server.
     * CoroutineScope.async.- Return Deffered<T>, to unwrapped
     * the value, use await().
     * Example: Network call (sequential/parallel)
     */
    private fun init() {
        scope.launch {
            val remoteSchoolList = network.service
                .getRemoteSchoolList()
            val remoteSat = network.service
                .getRemoteSat()
            // merge
            val mergedAsync = async {
                var merge = mutableListOf<SchoolSatEntity>()

                if (remoteSchoolList.isSuccessful &&
                    remoteSat.isSuccessful
                ) {
                    remoteSchoolList.body()?.let { schoolList ->
                        remoteSat.body()?.let { satScore ->
                            for (i in 0 until schoolList.size) {
                                for (j in 0 until satScore.size) {
                                    if (schoolList[i].dbn == satScore[j].dbn) {
                                        val schoolSat = SchoolSatEntity(
                                            schoolList[i].dbn,
                                            schoolList[i].school_name ?: "N/A",
                                            satScore[j].satTakers,
                                            satScore[j].reading,
                                            satScore[j].math,
                                            satScore[j].writing,
                                            schoolList[i].location ?: "N/A",
                                            schoolList[i].phone_number ?: "N/A",
                                            schoolList[i].fax_number ?: "N/A",
                                            schoolList[i].city ?: "N/A",
                                            schoolList[i].latitude ?: "N/A",
                                            schoolList[i].longitude ?: "N/A"
                                        )
                                        merge.add(schoolSat)
                                    }
                                }
                            }

                            insertToLocal(schoolList, satScore)
                        }
                    }
                }
                merge
            }
            this@Repository.merge.addAll(mergedAsync.await())

        }
        insertMergeList()
    }

    suspend fun getData(): List<SchoolSatEntity> {
        init()
        return nycDao.getPresentationData()
    }

    private fun insertMergeList() {
        scope.launch {
            merge.forEach {
                nycDao.insertSchoolSat(it)
            }
        }
    }

    private fun insertToLocal(
        schoolList: List<NYCSchool>,
        satScore: List<NYCSatScore>
    ) {
        scope.launch {
            satScore.forEach {
                nycDao.fetchSatList(it.toSatEntity())
            }
            schoolList.forEach {
                nycDao.fetchSchoolList(it.toSchoolEntity())
            }
        }
    }
}

private fun NYCSatScore.toSatEntity(): SatEntity {
    return SatEntity(
        this.dbn,
        this.school_name,
        this.satTakers,
        this.reading,
        this.math,
        this.writing
    )
}

private fun NYCSchool.toSchoolEntity(): SchoolEntity {
    return SchoolEntity(
        1,
        this.dbn,
        this.school_name ?: "N/A",
        this.location ?: "N/A",
        this.phone_number ?: "N/A",
        this.fax_number ?: "N/A",
        this.city ?: "N/A",
        this.latitude ?: "N/A",
        this.longitude ?: "N/A"
    )
}