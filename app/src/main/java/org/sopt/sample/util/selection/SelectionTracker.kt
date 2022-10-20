package org.sopt.sample.util.selection

import android.util.Log
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView

//RecyclerrView를 감시하면서 Select를 감지하고, Select된 ViewHolder의 Id를 저장하는 클래스
class SelectionTracker() {
    fun setupSelectionTracker(recyclerView: RecyclerView): SelectionTracker<Long>? {
        return (SelectionTracker.Builder(
            "selection_id",
            recyclerView,
            StableIdKeyProvider(recyclerView), //select된 viewholder의 id값을 가져오는 클래스
            SelectionDetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()
        )
            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build())
    }
}