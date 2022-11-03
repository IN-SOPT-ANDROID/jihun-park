package org.sopt.sample.util

import androidx.lifecycle.Observer
//1회성으로 이벤트를 전달해주기 위해 데이터를 Event Wrapper로 감싸줘야함.
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
