package com.kwk.epl

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.sqldelight.android.AndroidSqliteDriver
import comkwkeplsqldelighthockeydata.Player
import comkwkeplsqldelighthockeydata.PlayerQueries
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SQLDelightQueryTest {

    private lateinit var playerQueries: PlayerQueries
    private val context = InstrumentationRegistry.getInstrumentation().context

    @Before
    fun setUp() {
        val driver = AndroidSqliteDriver(Database.Schema, context, null)
        val database = Database(driver)
        playerQueries = database.playerQueries
    }

    @After
    fun clear() {
        playerQueries.clearPlayers()
    }

    @Test
    fun add_listOfPlayers_toPlayers() = runTest {
        val players = loadJsonFromAsset(context)
        playerQueries.clearPlayers()
        players.forEach {
            playerQueries.insert(it.playerNumber, it.playerFullName)
            Log.d("PlayerAdded :", it.playerFullName)
        }
        val result = playerQueries.selectAll().executeAsList()
        result.size shouldBe players.size
    }

    @Test
    fun add_singlePlayer_toPlayers() = runTest {
        playerQueries.clearPlayers()
        playerQueries.insert(7, "Mason Mount")
        val result = playerQueries.selectByPlayerNumber(7).executeAsOneOrNull()
        result shouldNotBe null
        result?.full_name shouldBe "Mason Mount"
    }

    @Test
    fun select_row_that_does_not_exist() = runTest {
        val playerNumberThatDoesNotExist = 1000L
        val result = playerQueries.selectByPlayerNumber(playerNumberThatDoesNotExist).executeAsOneOrNull()
        result shouldBe null
    }

    private fun loadJsonFromAsset(context: Context): List<PlayerModel> {
        val jsonString = context.assets.open("samples.json").bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }
}