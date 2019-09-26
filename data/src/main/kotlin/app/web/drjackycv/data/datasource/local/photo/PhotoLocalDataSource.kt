package app.web.drjackycv.data.datasource.local.photo

import app.web.drjackycv.data.datasource.local.base.RealmManager
import app.web.drjackycv.data.db.photo.PhotoDatabase
import app.web.drjackycv.data.db.photo.PhotoDatabaseFields
import app.web.drjackycv.data.db.photo.PhotoDatabaseModel
import app.web.drjackycv.domain.entity.base.Failure
import app.web.drjackycv.domain.entity.photo.Photo
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmModel
import javax.inject.Inject

class PhotoLocalDataSource @Inject constructor(
    private val realmManager: RealmManager
) {

    private lateinit var realm: Realm

    fun savePhotosList(photosList: List<Photo>): Completable = Completable.fromAction {
        realmManager.openLocalInstance()
        realm = realmManager.localInstance

        realm.executeTransaction { bgRealm ->
            val realmQuery = bgRealm.where(PhotoDatabase::class.java)

            realmQuery.findAll().deleteAllFromRealm()

            val map = photosList.map { PhotoDatabase(it) } as List<RealmModel>

            bgRealm.insertOrUpdate(map)
        }

        realmManager.closeLocalInstance()
    }

    fun getPhotosListByTags(tags: String): Single<List<Photo>> = Single.create { emitter ->
        realmManager.openLocalInstance()
        realm = realmManager.localInstance

        val realmQuery =
            realm.where(PhotoDatabase::class.java).equalTo(PhotoDatabaseFields.TITLE, tags)
        val realmResults = realmQuery.findAll()

        if (realmResults.isEmpty()) {
            emitter.onError(Failure.NotInDatabase)
        } else {
            emitter.onSuccess(realmResults.map { (it as PhotoDatabaseModel).toDomain() }.toList())
        }

        realmManager.closeLocalInstance()
    }

}