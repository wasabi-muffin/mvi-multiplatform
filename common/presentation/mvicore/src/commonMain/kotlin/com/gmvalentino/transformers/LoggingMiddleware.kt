package com.gmvalentino.transformers

import co.touchlab.kermit.Kermit
import com.gmvalentino.Action
import com.gmvalentino.ActionMiddleware
import com.gmvalentino.Intent
import com.gmvalentino.IntentMiddleware
import com.gmvalentino.Result
import com.gmvalentino.ResultMiddleware
import com.gmvalentino.State
import com.gmvalentino.StateMiddleware

object LoggingMiddleware : IntentMiddleware, ActionMiddleware, ResultMiddleware, StateMiddleware {
    override fun transform(intent: Intent): Intent {
        Kermit().d { "Intent: $intent" }
        return intent
    }

    override fun transform(action: Action): Action {
        Kermit().d { "Action: $action" }
        return action
    }

    override fun transform(result: Result): Result {
        Kermit().d { "Result: $result" }
        return result
    }

    override fun transform(state: State): State {
        Kermit().d { "State: $state" }
        return state
    }
}