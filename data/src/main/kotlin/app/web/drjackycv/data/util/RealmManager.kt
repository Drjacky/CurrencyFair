/*
 * Copyright 2017 Gabor Varadi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.web.drjackycv.data.util

import io.realm.Realm

/**
 * The RealmManager allows creating a singleton Realm manager which can open thread-local instances.
 *
 *
 * It also allows obtaining the open thread-local instance without incrementing the reference count.
 */
class RealmManager {

    private val localRealms = ThreadLocal<Realm>()

    /**
     * Returns the local Realm instance without adding to the reference count.
     *
     * @return the local Realm instance
     * @throws IllegalStateException when no Realm is open
     */
    val localInstance: Realm
        get() {
            val realm = localRealms.get()
            check(!(realm == null || realm.isClosed)) { "No open Realms were found on this thread." }
            return realm
        }

    /**
     * Opens a reference-counted local Realm instance.
     *
     * @return the open Realm instance
     */
    fun openLocalInstance(): Realm {
        checkDefaultConfiguration()
        val realm =
            Realm.getDefaultInstance() // <-- consider adding parameter and Map<RealmConfiguration, ...>
        val localRealm = localRealms.get()
        if (localRealm == null || localRealm.isClosed) {
            localRealms.set(realm)
        }
        return realm
    }

    /**
     * Closes local Realm instance, decrementing the reference count.
     *
     * @throws IllegalStateException if there is no open Realm.
     */
    fun closeLocalInstance() {
        checkDefaultConfiguration()
        val realm = localRealms.get()
        check(!(realm == null || realm.isClosed)) { "Cannot close a Realm that is not open." }
        realm.close()

        if (Realm.getLocalInstanceCount(Realm.getDefaultConfiguration()!!) <= 0) {
            localRealms.set(null)
        }
    }

    private fun checkDefaultConfiguration() {
        checkNotNull(Realm.getDefaultConfiguration()) { "No default configuration is set." }
    }

}