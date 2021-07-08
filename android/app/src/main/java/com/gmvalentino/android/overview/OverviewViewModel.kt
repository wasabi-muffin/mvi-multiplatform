package com.gmvalentino.android.overview

import com.gmvalentino.android.BaseViewModel
import com.gmvalentino.overview.components.OverviewStore
import com.gmvalentino.overview.contract.OverviewEvent
import com.gmvalentino.overview.contract.OverviewIntent
import com.gmvalentino.overview.contract.OverviewState

class OverviewViewModel(
    store: OverviewStore
) : BaseViewModel<OverviewIntent, OverviewState, OverviewEvent>(store)
