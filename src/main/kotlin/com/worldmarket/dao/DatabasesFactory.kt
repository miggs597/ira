package com.worldmarket.dao

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {
    fun init() {
        val database = Database.connect(
            url = "jdbc:sqlserver://sql-loyalty-qa-ncus-001.database.windows.net:1433;database=sqldb-imagerendering-qa-ncus;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            user = "c360qasqladmin@sql-loyalty-qa-ncus-001",
            password = "wmlp@#c360Qa!"
        )
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}