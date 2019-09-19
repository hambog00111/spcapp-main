package layout.ph.sanpablocitygov.iSanPablo.cityhotlines

class CityhotlineModel( ivcityhotline : String ,  ivcityhotlinephone : String)

{

    var ivcityhotline: String
        internal set
    var ivcityhotlinephone: String
        internal set
    init {
        this.ivcityhotline = ivcityhotline

        this.ivcityhotlinephone = ivcityhotlinephone
    }
}