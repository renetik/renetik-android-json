package renetik.android.json.obj

import renetik.android.core.CSApplication.Companion.app
import renetik.android.core.java.io.readText
import renetik.android.core.kotlin.reflect.createInstance
import renetik.android.json.parseJsonMap
import renetik.android.json.toJSONObject
import java.io.File

fun CSJsonObject.toJsonObject() = data.toJSONObject()

fun CSJsonObject(data: String) = CSJsonObject().load(data)

fun <T : CSJsonObject> T.load(json: String) = apply { load(json.parseJsonMap()!!) }

fun <T : CSJsonObject> T.load(file: File) = load(file.readText())

fun <T : CSJsonObject> T.loadAsset(file: String) =
    load(app.assets.open(file).readText())

fun CSJsonObject.getObject(key: String) = getMap(key)?.let(CSJsonObject()::load)

fun <T : CSJsonObject> T.clone() =
    this::class.createInstance()!!.also { it.load(this.data) }