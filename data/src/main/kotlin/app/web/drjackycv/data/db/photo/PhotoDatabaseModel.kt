package app.web.drjackycv.data.db.photo

import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.Photo
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

interface PhotoDatabaseModel : ResponseObject<Photo> {
    var id: String
    var owner: String
    var secret: String
    var server: String
    var farm: Int
    var title: String
    var ispublic: Int
    var isfriend: Int
    var isfamily: Int

    override fun toDomain(): Photo =
        Photo(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            ispublic = ispublic,
            isfriend = isfriend,
            isfamily = isfamily
        )

}

@RealmClass
open class PhotoDatabase(
    @PrimaryKey
    override var id: String,
    override var owner: String,
    override var secret: String,
    override var server: String,
    override var farm: Int,
    override var title: String,
    override var ispublic: Int,
    override var isfriend: Int,
    override var isfamily: Int
) : RealmModel, PhotoDatabaseModel {

    constructor() : this("-1", "", "", "", -1, "", -1, -1, -1)

    constructor(photo: Photo) : this(
        id = photo.id,
        owner = photo.owner,
        secret = photo.secret,
        server = photo.server,
        farm = photo.farm,
        title = photo.title,
        ispublic = photo.ispublic,
        isfriend = photo.isfriend,
        isfamily = photo.isfamily
    )

}