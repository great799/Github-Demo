package com.app.githubdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.app.githubdemo.R

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ViewDataBinding
    private lateinit var viewModel: ViewModel
    private var alertDialog: AlertDialog? = null

    abstract fun getLayoutId(): Int
    abstract fun getViewModelClass(): Class<out ViewModel>
    abstract fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String)
    abstract fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, tag: String)

    protected fun getViewModel(): ViewModel {
        return viewModel
    }

    protected fun getBinding(): ViewDataBinding {
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
    }

    fun showLoading() {
        if (alertDialog == null) {
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            var view = LayoutInflater.from(this).inflate(R.layout.layout_loading, null)
            builder.setView(view)
            builder.setCancelable(false)
            alertDialog = builder.create()
        }
        alertDialog?.show()
    }

    fun hideLoading() {
        if (alertDialog != null) {
            alertDialog?.dismiss()
        }
    }

    fun showToast(message: String, length: Int) {
        Toast.makeText(this, message, length).show()
    }
}