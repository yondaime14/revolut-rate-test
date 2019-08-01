package com.carllewis14.revoluttest.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carllewis14.revoluttest.R
import com.carllewis14.revoluttest.base.Injector
import com.carllewis14.revoluttest.viewmodel.RevolutRatesViewModel
import kotlinx.android.synthetic.main.fragment_rates.*
import javax.inject.Inject

class CurrenciesFragment : Fragment() {

    @Inject
    lateinit var mViewModel: RevolutRatesViewModel

    var mRatesAdapter: CurrenciesAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance() = CurrenciesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        Injector.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRatesAdapter = CurrenciesAdapter()
        var layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        recycler.adapter = mRatesAdapter

        mRatesAdapter?.setBaseCurrencySubject(mViewModel.getBaseCurrencySubject())

        mViewModel.getCurrenciesSubject()
            .doOnNext {
                mRatesAdapter?.swap(it)
                if(mViewModel.getShowProgress().value!!){
                    recycler.scrollToPosition(0)
                }
            }
            .subscribe()

        mViewModel.getShowProgress().doOnNext {
            loading.visibility = if(it) View.VISIBLE else View.GONE
        }.subscribe()

    }

    override fun onStart() {
        super.onStart()
        mViewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        mViewModel.onStop()
    }

}
