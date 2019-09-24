package app.web.drjackycv.data.db.photo

import app.web.drjackycv.domain.entity.base.ResponseObject
import app.web.drjackycv.domain.entity.photo.Photo
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

interface PhotoDatabaseModel : ResponseObject<Photo> {
    val id: String
    val owner: String
    val secret: String
    val server: String
    val farm: Int
    val title: String
    val ispublic: Int
    val isfriend: Int
    val isfamily: Int

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
    override val id: String,
    override val owner: String,
    override val secret: String,
    override val server: String,
    override val farm: Int,
    override val title: String,
    override val ispublic: Int,
    override val isfriend: Int,
    override val isfamily: Int
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