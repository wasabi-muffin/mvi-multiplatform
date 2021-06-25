package com.gmvalentino.transformers

import co.touchlab.kermit.Kermit
import com.gmvalentino.Action
import com.gmvalentino.ActionTransformer
import com.gmvalentino.Result
import com.gmvalentino.ResultTransformer

object LoggingMiddleware : ActionTransformer, ResultTransformer {
    override fun transform(data: Action): Action {
        Kermit().d { "Action: $data" }
        return data
    }

    override fun transform(data: Result): Result {
        Kermit().d { "Result: $data" }
        return data
    }
}