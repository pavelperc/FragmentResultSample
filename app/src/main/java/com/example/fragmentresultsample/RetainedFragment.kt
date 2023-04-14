package com.example.fragmentresultsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

open class RetainedFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected fun registerActivityLauncher(onResult: (ActivityResult) -> Unit): ActivityResultLauncher<Intent> {
        var innerLauncher: ActivityResultLauncher<Intent>? = null

        onAttachListeners += {
            innerLauncher = registerForActivityResult(StartActivityForResult(), onResult)
        }

        return object : ActivityResultLauncher<Intent>() {
            override fun launch(input: Intent?, options: ActivityOptionsCompat?) {
                innerLauncher?.launch(input, options) ?: error("not attached to activity")
            }

            override fun unregister() {
                innerLauncher?.unregister() ?: error("not attached to activity")
            }

            override fun getContract(): ActivityResultContract<Intent, *> {
                return innerLauncher?.contract ?: error("not attached to activity")
            }
        }
    }

    private val onAttachListeners = mutableListOf<() -> Unit>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachListeners.forEach { it.invoke() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}