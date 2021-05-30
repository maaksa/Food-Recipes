package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.user

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.user.UserDao
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.user.UserEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.User
import timber.log.Timber

class UserRepositoryImpl(
    private val localDataSource: UserDao
) : UserRepository {

    override fun insert(entity: User): Completable {
        val userEntity = UserEntity(
            0,
            entity.name,
            entity.password,

            )
        return localDataSource.insert(userEntity)
    }

    override fun getByPin(): Observable<Int> {
        return localDataSource.getByPin()
    }

}


