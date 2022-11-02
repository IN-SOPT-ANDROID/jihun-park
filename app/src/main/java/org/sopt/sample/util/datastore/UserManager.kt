package org.sopt.sample.util.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.sopt.sample.presentation.model.UserInfo
import java.io.IOException


class UserManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "InSoptDataStore")

    //data저장 혹은 로드 시에 사용할 데이터 종류마다 만들어놓은 키
    companion object {
        val USER_ID_KEY = stringPreferencesKey("USER_ID")
        val USER_PW_KEY = stringPreferencesKey("USER_PW")
        val USER_MBTI_KEY = stringPreferencesKey("USER_MBTI")
    }
    //키를 이용하여 Flow형태로 데이터 반환
    val userIdFlow: Flow<String> = context.dataStore.data
        .catch {e->
            if(e is IOException){
                emit(emptyPreferences())
            } else{
                throw e
            }
        } .map {
        it[USER_ID_KEY]?:""
    }
    val userPwFlow: Flow<String> = context.dataStore.data
        .catch {e->
            if(e is IOException){
                emit(emptyPreferences())
            } else{
                throw e
            }
        } .map {
        it[USER_PW_KEY]?:""
    }
    val userMbtiFlow: Flow<String> = context.dataStore.data
        .catch {e->
            if(e is IOException){
                emit(emptyPreferences())
            } else{
                throw e
            }
        } .map {
            it[USER_MBTI_KEY]?:""
    }

    suspend fun setUserInfo(id: String, pw: String, mbti: String) {
        //DataStore에 값을 쓸 때에는 반드시 비동기로 작업해야하기 때문에, 해당 함수가 코루틴 영역해서 동작할 수 있도록 suspend로 정의
        context.dataStore.edit {
            it[USER_ID_KEY] = id
            it[USER_PW_KEY] = pw
            it[USER_MBTI_KEY] = mbti
        }
    }
    suspend fun getUserInfo(): UserInfo? {
        // id가 존재하지 않는 경우 null 반환
        if(userIdFlow.first().isEmpty()){
            return null
        }
        return UserInfo(userIdFlow.first(),userPwFlow.first(),userMbtiFlow.first())
    }

}