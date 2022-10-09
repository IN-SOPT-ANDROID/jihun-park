# JiHun

## This is your repository for 31st SOPT Android Development Seminar and Assignment

### 레포지터리에는 총 3개의 브랜치가 있습니다.

- master
    - 가장 기본이 되는 브랜치입니다. 새로운 연습 환경을 만들고자 할때 이 브랜치에서 새로운 브랜치를 파셔서 만드시면 됩니다.
- develop/view
    - 여러분의 대부분의 과제는 이 브랜치에서 이뤄지게 될 것입니다.
    - develop/view 브랜치에서 새로운 feature 브랜치를 파고 작업을 진행하면서
    - 과제를 완료하면 해당 과제를 develop/view에 PR을 올려주시고 코드리뷰를 받으시면 됩니다.
    - 머지까지 완료하시면 과제 완료로 인정하겠습니다.
- develop/compose
    - 이번 기수 심화과제인 Compose 과제를 진행하는 브랜치입니다.
    - develop/compose 브랜치에서 심화과제에 해당하는 내용의 작업들을 진행해주시면 됩니다.
    - 기존 view 브랜치들과는 연관관계가 없어야 합니다.
    - 과제를 완료하면 해당 과제를 develop/compose에 PR을 올려주시고 코드리뷰를 받으시면 됩니다.
    - Compose 개발환경은
        - Kotlin: 1.7.0
        - Compose: 1.2.1
    - 으로 맞춰놓겠습니다.

<p align="center">
<img src ="https://user-images.githubusercontent.com/70442964/194756551-2619de6a-b0df-4082-ac33-2c30c60d7436.gif" height="500"
</p?



### ConstraintLayout - Chain의 속성
  ![img_1.png](img_1.png)
- Spread : 체인간의 일정한 거리를 유지한 채로 정렬된다.
- Spread inside : start, end의 컴포넌트가 각각 부모쪽으로 붙으며 정렬된다.
- Weighted : wieght만큼의 비율을 정해서 공간을 차지하도록 함. LinearLayout의 weight와 유사하다.
- Packed : 컴포넌트들이 여백없이 붙어서 정렬된다.

공식문서 참조(https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html#Chains)

### Activity LifeCycle
 - Activity클래스는 시스템 활동의 상태 변화(생성, 재개, 중단, 재시작, 종료)를 알아차릴 수 있는 콜백을 제공한다.
 ex) 유튜브를 보던 중 카카오톡 실행 -> 유튜브 영상 일시중지 및 네트워크 연결 종료 -> 유튜브 앱으로 돌아오면 동영상이 중지된 지점부터 재생 및 네트워크 재연결
 - 생명주기가 중요한 이유!
  * 다른 앱으로 전환하는 경우 비정상 종료되는 문제 ex) 전화 수신
  * 사용중이지 않은 앱에 대한 시스템 리소스의 낭비 문제
  * 앱에서 나갔다가 다시 돌아왔을 때, 사용자의 진행 상태가 저장되지 않는 문제
  * 화면의 가로 방향, 세로 방향 회전될 경우 비정상 종료되거나 사용자의 진행 상태가 저장되지 않는 문제
  -> 이상의 문제들을 예방할 수 있음
![activity_lifecycle](https://user-images.githubusercontent.com/70442964/194318635-4b862ee8-8c21-418f-88de-cdd10c6ca0e7.png)

 ## OnCreate()
  - 액티비티 생성 시 실행되는 콜백
  - 수명 주기 동안 한 번만 발생해야하는 기본 어플리케이션 시작 로직 실행 
   -> ex) savedInstanceState:Bundle(활동의 이전 저장 상태, 최초 생성된 경우 Bundle객체의 값은 null)
  - setContentView(xml 레이아웃 파일의 리소스 ID가 전달됨)
```Kotlin
  lateinit var textView: TextView
// some transient state for the activity instance
var gameState: String? = null

override fun onCreate(savedInstanceState: Bundle?) {
    // call the super class onCreate to complete the creation of activity like
    // the view hierarchy
    super.onCreate(savedInstanceState)

    // onSaveInstanceState에서 저장된 instance state를 복구시킴(최초 생성 시엔 null)
    gameState = savedInstanceState?.getString(GAME_STATE_KEY)

    // set the user interface layout for this activity
    // the layout file is defined in the project res/layout/main_activity.xml file
    setContentView(R.layout.main_activity)

    // initialize member TextView so we can manipulate it later
    textView = findViewById(R.id.text_view)
}

// This callback is called only when there is a saved instance that is previously saved by using
// onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
// other state here, possibly usable after onStart() has completed.
// The savedInstanceState Bundle is same as the one used in onCreate().
override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    textView.text = savedInstanceState?.getString(TEXT_VIEW_KEY)
}
// 액티비티가 일시적으로 destory 됐을 때, instance state(outState)를 저장한다.
override fun onSaveInstanceState(outState: Bundle?) {
    outState?.run {
        putString(GAME_STATE_KEY, gameState)
        putString(TEXT_VIEW_KEY, textView.text.toString())
    }
    // call superclass to save any view hierarchy
    super.onSaveInstanceState(outState)
}
```

 ## onStart()
  - UI를 관리하는 코드 초기화
  - 매우 빠르게 완료되고 곧바로 onResume() 메서드 호출

 ## onResume()
  - 앱과 사용자가 상호작용하는 상태(사용자에게 보이는 기능 활성화 가능 ex) 카메라 미리보기 시작)
  - 앱에서 포커스가 떠날 때(전화 수신, 다른 앱 전환, 기기 화면 꺼짐)까지 이 상태에 머무른다.
  - 포커스가 떠나게 되면 nPause() 콜백 호출, 다시 돌아오면 onResume() 콜백을 다시 한번 호출
  - 주로 onPause에서 해제한 구성요소를 onResume에서 다시 초기화한다.
  
```Kotlin  
 class CameraComponent : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun initializeCamera() {
        if (camera == null) {
            getCamera()
        }
    }
}
```

 ## onPause
  - 앱 실행을 방해하는 일부 이벤트로 인해 액티비티에서 포커스가 떠나게 되면 이 메서드를 호출 ex) 카메라 미리보기 정지
  - 필요하지 않은 모든 리소스를 해제할 수도 있지만, 멀티 윈도우 모드에서 여전히 보이는 상태일 수 있으므로,
    UI관련 리소스와 작업을 완전히 해제하거나 조정할 때에는 onPause() 대신 onStop()을 사용하는 것이 좋다.
```Kotlin   
class CameraComponent : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun releaseCamera() {
        camera?.release()
        camera = null
    }
}
```
onPause()는 아주 잠깐 실행되므로 저장 작업을 실행하기에는 시간이 부족할 수 있다.
따라서 onPause()를 사용하여 애플리케이션, 사용자 데이터의 저장, 네트워크 호출, 데이터베이스 트랜잭션을 실행해선 **안된다!**
사용자에게 완전히 보이지 않을 때까지 이 상태에 머무르다가 활동이 시작되면 onResume() 콜백을 호출한다.
활동이 완전히 보이지 않게 된다면 시스템은 onStop()을 호출한다.

 ## onStop
  - 활동의 실행이 완료되어 종료될 시점 또는 활동이 사용자에게 더 이상 표시되지 않을 때 호출된다. ex) 새로 시작된 다른 활동이 화면 전체를 차지하는 경우
  - 구성요소가 화면에 보이지 않을 때 실행할 필요가 없는 기능을 모두 정지할 수 있음
  - 필요하지 않은 리소스를 해제하거나 조정할 때 호출 ex) 애니메이션 일시중지, 세밀한 위치 업데이트 -> 대략적인 위치 업데이트
  - onPause() 대신 onStop()을 사용하면 멀티 윈도우 모드에서 활동을 보고 있더라도 UI 관련 작업이 계속 진행된다.
  - CPU를 비교적 많이 소모하는 종료 작업을 실행해야한다. EX) DB에 정보를 저장하는 작업

```Kotlin
    override fun onStop() {
    // call the superclass method first
    super.onStop()
    // 액티비티가 중지될 때 노트의 현재 진행상황을 잃지 않기 위해 노트의 내용을 저장
    val values = ContentValues().apply {
        put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText())
        put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle())
    }
    // 백그라운드에서의 업데이트 작업
    // do this update in background on an AsyncQueryHandler or equivalent
    asyncQueryHandler.startUpdate(
            token,     // int token to correlate calls
            null,      // cookie, not used here
            uri,       // The URI for the note to update.
            values,    // The map of column names and new values to apply to them.
            null,      // No SELECT criteria are used.
            null       // No WHERE columns are used.
    )
}
```

활동이 onStop() 상태가 되면 액티비티 객체는 메모리 안에 머무르게 된다.
참고: 활동이 중단되면 시스템은 해당 활동이 포함된 프로세스를 소멸시킬 수 있습니다(시스템이 메모리를 복구해야 하는 경우). 활동이 중단된 동안 시스템이 프로세스를 소멸시키더라도 Bundle(키-값 쌍의 blob)에 있는 View 객체(예: EditText 위젯의 텍스트) 상태가 그대로 유지되고, 사용자가 이 활동으로 돌아오면 이를 복원합니다. 사용자가 돌아올 때의 활동 복원에 관한 자세한 내용은 활동 상태 저장 및 복원을 참조하세요.
활동은 정지됨 상태에서 다시 시작되어 사용자와 상호작용하거나, 실행을 종료하고 사라짐. 
활동이 다시 시작되면 시스템은 onRestart()를 호출하고 Activity가 실행을 종료하면 시스템은 onDestroy()를 호출

 ## onDestroy
  -  액티비티가 소명되기 전에 호출
   1. 사용자가 앱을 끄거나, finish()가 호출되어 액티비티가 종료되는 경우
   2. 기기 회전 또는 멀티윈도우 모드로 인해 시스템이 일시적으로 활동을 소멸시키는 경우
  - onStop()에서 아직 해제되지 않은 모든 리소스 해제


![9913FB4C5A9CAE1919](https://user-images.githubusercontent.com/70442964/194336951-f0152536-e072-4468-9497-334796a15668.png)

