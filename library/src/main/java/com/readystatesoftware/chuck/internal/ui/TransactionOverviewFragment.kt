/*
 * Copyright (C) 2017 Jeff Gilfelt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.readystatesoftware.chuck.internal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.readystatesoftware.chuck.R
import com.readystatesoftware.chuck.internal.data.HttpTransaction
import kotlinx.android.synthetic.main.chuck_fragment_transaction_overview.*

class TransactionOverviewFragment : Fragment(), TransactionFragment {

    private var transaction: HttpTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.chuck_fragment_transaction_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateUI()
    }

    override fun transactionUpdated(transaction: HttpTransaction) {
        this.transaction = transaction
        populateUI()
    }

    private fun populateUI() {
        if (isAdded && transaction != null) {
            url.text = transaction!!.url
            method.text = transaction!!.method
            protocol.text = transaction!!.protocol
            status.text = transaction!!.status.toString()
            response.text = transaction!!.responseSummaryText
            ssl.setText(if (transaction!!.isSsl) R.string.chuck_yes else R.string.chuck_no)
            request_time.text = transaction!!.requestDateString
            response_time.text = transaction!!.responseDateString
            duration.text = transaction!!.durationString
            request_size.text = transaction!!.requestSizeString
            request_size.text = transaction!!.responseSizeString
            total_size.text = transaction!!.totalSizeString
        }
    }
}
