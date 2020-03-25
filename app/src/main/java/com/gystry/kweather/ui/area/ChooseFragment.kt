package com.gystry.kweather.ui.area

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gystry.kweather.R
import com.gystry.kweather.databinding.ChooseAreaBindingImpl
import com.gystry.kweather.ui.MainActivity
import com.gystry.kweather.util.InjectorUtil
import kotlinx.android.synthetic.main.choose_area.*

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class ChooseFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this,
            InjectorUtil.getChooseAreaModelFactory()
        ).get(ChooseAreaViewModel::class.java)
    }

    private var progressDialog: ProgressDialog? = null

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.choose_area, container, false)
        val bind = DataBindingUtil.bind<ChooseAreaBindingImpl>(view)
        bind?.viewModel = viewModel
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ChooseAreaAdapter(context!!, R.layout.simple_item, viewModel.dataList)
        listView.adapter = adapter
        observe()
    }

    private fun observe() {
        viewModel.currentLevel.observe(this, Observer {
            when (it) {
                LEVEL_PROVINCE -> {
                    tv_title.text = "中国"
                    bt_back.visibility = View.GONE
                }
                LEVEL_CITY -> {
                    tv_title.text = viewModel.selectedProvince?.provinceName
                    bt_back.visibility = View.VISIBLE
                }
                LEVEL_COUNTY -> {
                    tv_title.text = viewModel.selectedCity?.cityName
                    bt_back.visibility = View.VISIBLE
                }
            }
        })
        viewModel.dataChanged.observe(this, Observer {
            adapter.notifyDataSetChanged()
            listView.setSelection(0)
            closeProgressDialog()
        })
        viewModel.isLoading.observe(this, Observer {
            if (it) showProgressDialog()
            else closeProgressDialog()
        })

        viewModel.areaSelected.observe(this, Observer {
            if (it && viewModel.selectedCounty != null) {
                if (activity is MainActivity) {

                } else if (activity is MainActivity) {

                }
                viewModel.areaSelected.value = false
            }
        })
        if (viewModel.dataList.isEmpty()) {
            viewModel.getProvinces()
        }
    }

    /**
     * 显示进度对话框
     */
    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(activity)
            progressDialog?.setMessage("正在加载...")
            progressDialog?.setCanceledOnTouchOutside(false)
        }
        progressDialog?.show()
    }

    /**
     * 关闭进度对话框
     */
    private fun closeProgressDialog() {
        progressDialog?.dismiss()
    }

    companion object {
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }
}