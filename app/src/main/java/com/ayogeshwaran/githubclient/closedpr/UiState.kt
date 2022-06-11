/*
 * ******************************************************************************
 *  * Copyright (C)  2021 VMware, Inc. All rights reserved.
 *  * This product is protected by copyright and intellectual property laws in
 *  * the United States and other countries as well as by international treaties.
 *  * VMware products may be covered by one or more patents listed at http://www.vmware.com/go/patents.
 *  ******************************************************************************
 *
 */

package com.ayogeshwaran.githubclient.closedpr

import androidx.annotation.StringRes

sealed class UiState<T> {
    /**
     * Indicates the operation succeeded with a data list.
     *
     * @param data List to be shown in the UI.
     */
    class Success<T>(
        val
        data: T
    ) : UiState<T>()

    /**
     * Indicates the operation succeeded with no data.
     */
    object NoData : UiState<Any>()

    /**
     * Indicates the operation is going on.
     */
    object Loading : UiState<Any>()

    /**
     * Indicates the operation failed with an error message ID.
     *
     * @param errorMessageId The ID to find the string resource.
     */
    class Error(@StringRes val errorMessageId: Int) : UiState<Any>()
}