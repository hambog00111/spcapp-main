package ph.sanpablocitygov.iSanPablo.search

class Model(title: String, icon: Int) {



    var title: String
        internal set
    var icon: Int = 0
        internal set

    init {
        this.title = title

        this.icon = icon
    }
}