import scala.io.Source

/**
 * Created by Anders Bech Mellson on 03/11/13.
 */

object parser {
  def main(args: Array[String]) {
    // The unique part of the url to your google drive document (easy to identify in your browser).
    // Important the document needs to be visible for everyone that has the URL.
    val documentID = "1SxHQY8nxqqiyT5-H6NVs_yMVYqD_-53NKqkpPcuObvw"

    val txt = htmlFromSource(documentID)
    println(txt)
  }

  def downloadDocument(documentID: String): Source = {
    val url = "https://docs.google.com/feeds/download/documents/export/Export?id=" + documentID + "&exportFormat=txt"
    Source.fromURL(url, "UTF-8")
  }

  def htmlFromSource(documentID: String) = {
    var html = ""
    val txtLines = downloadDocument(documentID).getLines()
    var firstLineParsed = false
    // Value used to remove the byte order mark (BOM) from the first string coming from Google Drive.
    val bom = Source.fromBytes(Array(0xEF.asInstanceOf[Byte], 0xBB.asInstanceOf[Byte], 0xBF.asInstanceOf[Byte]), "UTF-8").mkString
    for (txt <- txtLines) {
      if (!firstLineParsed) {
        firstLineParsed = true
        html += parseHelper(txt.replace(bom,""),0) + '\n'
      } else
        html += parseHelper(txt, 0) + '\n'
    }

    // ParseHelper will parser the txt in markdown similar fashion.
    // It is much more simple than markdown in it's current state.
    // Start a line with one or more hash tags to get the according header level
    // Start with * or _ for emphasis
    def parseHelper(s: String, h: Int): String = (s.headOption, h) match {
      case (Some('#'), _) => parseHelper(s.tail, h + 1)
      case (Some('*'), _) => "<em>" + s.tail + "</em>"
      case (Some('_'), _) => "<em>" + s.tail + "</em>"
      case (_, 0) => s
      case (_, x) => "<h" + x + ">" + s + "</h" + x + ">"
    }
    html
  }
}
