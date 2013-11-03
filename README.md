The Google Drive Scala Txt Parser is a small utility I use for getting raw text from Google Drive documents.

I use it for customers who wish to maintain website content from a Drive document.

There is a very simple parser included in this sbt Scala project.

ParseHelper will parser the txt in markdown similar fashion.
It is much more simple than markdown in it's current state.
Start a line with one or more hash tags to get the according header level
Start with * or _ for emphasis

The document needs to be visible for everyone that has the URL.
Set the sharing option accordingly on Drive.

You need the unique part of the url to your google drive document (easy to identify in your browser).
For instance the document id for this url "https://docs.google.com/a/bechmellson.com/document/d/1SxHQY8nxqqiyT5-H6NVs_yMVYqD_-53NKqkpPcuObvw/edit" is "1SxHQY8nxqqiyT5-H6NVs_yMVYqD_-53NKqkpPcuObvw"