# order-proceccing-java

A feladat leírásában szereplő "order" tábla neve nálam order_java, mert az order a mysql számára fenntartott név.

Konfigurációs fájl:

DatabaseURL= ebből az urlből szükség van paraméterekre (timezone, stb)
DatabaseDriver=com.mysql.cj.jdbc.Driver
ResponeFileNameLocal= ide fogja elhelyezni a responseFilet (relatív elérési útovalat adtam meg)
InputFileName= elérési útvonal és név
FTPResponseFileName= FTP-re felkerülő fájl neve

A szükséges könyvtárak a lib mappában találhatók, ezek a build path-tal konfigurálhatók.
