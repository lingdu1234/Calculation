package top.iu1314.calculation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import java.util.*

class MyViewModel(application: Application, handle: SavedStateHandle) :
    AndroidViewModel(application) {
    private val handle: SavedStateHandle
    var winFlag = false
    val leftNumber: MutableLiveData<Int>
        get() = handle.getLiveData(KEY_LEFT_NUMBER)
    val rightNumber: MutableLiveData<Int>
        get() = handle.getLiveData(KEY_RIGHT_NUMBER)
    val operator: MutableLiveData<String>
        get() = handle.getLiveData(KEY_OPERATOR)
    val highScore: MutableLiveData<Int?>
        get() = handle.getLiveData(KEY_HIGH_SCORE)
    val currentScore: MutableLiveData<Int>
        get() = handle.getLiveData(KEY_CURRENT_HIGH_SCORE)
    val answer: MutableLiveData<Int>
        get() = handle.getLiveData(KEY_ANSWER)

    fun generator() {
        val LEVEL = 20
        val random = Random()
        val x: Int
        val y: Int
        x = random.nextInt(LEVEL) + 1
        y = random.nextInt(LEVEL) + 1
        if (x % 2 == 0) {
            operator.value = "+"
            if (x > y) {
                answer.value = x
                leftNumber.value = y
                rightNumber.setValue(x - y)
            } else {
                answer.value = y
                leftNumber.value = x
                rightNumber.setValue(y - x)
            }
        } else {
            operator.value = "-"
            if (x > y) {
                answer.value = x - y
                leftNumber.value = x
                rightNumber.setValue(y)
            } else {
                answer.value = y - x
                leftNumber.value = y
                rightNumber.setValue(x)
            }
        }
    }

    fun save() {
        val shp = getApplication<Application>().getSharedPreferences(
            SAVE_SHP_DATA_NAME,
            Context.MODE_PRIVATE
        )
        val editor = shp.edit()
        editor.putInt(
            KEY_HIGH_SCORE,
            highScore.value!!
        )
        editor.apply()

    }

    fun answerCorrect() {
        currentScore.value = currentScore.value!! + 1
        if (currentScore.value!! > highScore.value!!) {
            highScore.value = currentScore.value
            winFlag = true
        }
        generator()
    }

    companion object {
        private const val KEY_HIGH_SCORE = "key_high_score"
        private const val KEY_CURRENT_HIGH_SCORE = "key_current_high_score"
        private const val KEY_LEFT_NUMBER = "key_left_number"
        private const val KEY_RIGHT_NUMBER = "key_right_number"
        private const val KEY_OPERATOR = "key_operator"
        private const val KEY_ANSWER = "key_answer"
        private const val SAVE_SHP_DATA_NAME = "save_shp_data_name"
    }

    init {
        if (!handle.contains(KEY_HIGH_SCORE)) {
            val shp = getApplication<Application>().getSharedPreferences(
                SAVE_SHP_DATA_NAME,
                Context.MODE_PRIVATE
            )
            handle.set(KEY_HIGH_SCORE, shp.getInt(KEY_HIGH_SCORE, 0))
            handle.set(KEY_LEFT_NUMBER, 0)
            handle.set(KEY_RIGHT_NUMBER, 0)
            handle.set(KEY_OPERATOR, '+')
            handle.set(KEY_ANSWER, 0)
            handle.set(KEY_CURRENT_HIGH_SCORE, 0)
        }
        this.handle = handle
    }
}