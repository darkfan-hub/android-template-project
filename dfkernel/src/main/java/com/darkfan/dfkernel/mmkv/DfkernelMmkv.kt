package com.darkfan.dfkernel.mmkv

import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 08/05/2022 13:58
 * @desc
 */
interface DfkernelMmkv {
    val mmkv: MMKV get() = com.darkfan.dfkernel.mmkv.mmkv
}

val mmkv: MMKV by lazy { MMKV.defaultMMKV() }

fun boolean(default: Boolean = false) =
    MMKVProperty(MMKV::decodeBool, MMKV::encode, default)

fun int(default: Int = 0) =
    MMKVProperty(MMKV::decodeInt, MMKV::encode, default)

fun long(default: Long = 0) =
    MMKVProperty(MMKV::decodeLong, MMKV::encode, default)

fun float(default: Float = 0f) =
    MMKVProperty(MMKV::decodeFloat, MMKV::encode, default)

fun double(default: Double = 0.0) =
    MMKVProperty(MMKV::decodeDouble, MMKV::encode, default)

fun string(default: String = "") =
    MMKVProperty(MMKV::decodeString, MMKV::encode, default)

fun bytes(default: ByteArray) =
    MMKVProperty(MMKV::decodeBytes, MMKV::encode, default)

fun stringSet(default: Set<String>) =
    MMKVProperty(MMKV::decodeStringSet, MMKV::encode, default)

class MMKVProperty<V>(
    private val decode: MMKV.(String, V) -> V,
    private val encode: MMKV.(String, V) -> Boolean,
    private val default: V
) : ReadWriteProperty<DfkernelMmkv, V> {

    override fun getValue(thisRef: DfkernelMmkv, property: KProperty<*>): V {
        return thisRef.mmkv.decode(property.name, default)
    }

    override fun setValue(thisRef: DfkernelMmkv, property: KProperty<*>, value: V) {
        thisRef.mmkv.encode(property.name, value)
    }
}