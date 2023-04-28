package renetik.android.json
//TODO: Rename to cloneJsonMap ?
fun Map<String, Any?>.cloneDeep() =
    toJson(forceString = false, formatted = false).parseJsonMap()