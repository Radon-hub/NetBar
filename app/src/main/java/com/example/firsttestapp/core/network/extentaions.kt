package com.example.firsttestapp.core.network

inline fun<T, X> DataHolder<T>.mapIfSuccess(block: (T) -> X): DataHolder<X> {
    return when(this) {
        is DataHolder.Error -> {
            DataHolder.Error(this.data, this.meta)
        }

        is DataHolder.Success -> {
            DataHolder.Success(block(this.data), this.meta)
        }
    }
}


suspend inline fun DataHolder<*>.doOnError(crossinline block: suspend (MetaEntity?) -> Unit): DataHolder<*> {
    if(this is DataHolder.Error) {
        block(this.meta)
    }

    return this
}

suspend inline fun<T> DataHolder<T>.doOnSuccess(crossinline block: suspend (T) -> Unit): DataHolder<T> {
    if(this is DataHolder.Success<T>) {
        block(this.data)
    }

    return this
}