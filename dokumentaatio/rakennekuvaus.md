### Rakennekuvaus

Ohjelman rakenne on mielek‰st‰ jakaa kolmeen osaan: ohjelmaikkunaluokat, pulmanpiirtoluokat sek‰ pulmaluokat. [Luokkakaaviossa](/dokumentaatio/luokkakaavio.png) jaottelu kulkee vasemmalta oikealle.

#### Pulmaluokat

Pulmaluokat lˆytyv‰t [luokkakaaviosta](/dokumentaatio/luokkakaavio.png) oikealta puolelta, sis‰lt‰en kaikki luokat `Grid`-luokasta l‰htien oikealle.

Ohjelman syd‰mess‰ on abstrakti `Puzzle`-luokka, joka esitt‰‰ yleist‰ pulmaa, ja sen perim‰ll‰ toteutetaan tietty pulmatyyli. Se sis‰lt‰‰ seuraavat komponentit:

 - Luokka `Grid` pit‰‰ sis‰ll‰‰n itse pulmaruudukon. Sek‰ ruuduissa, ruutujen reunoissa ett‰ ruutujen kulmissa voi olla arvoja. Kaikki pulmatyylit eiv‰t v‰ltt‰m‰tt‰ k‰yt‰ kaikkia kolmea: esimerkiksi Sudoku tallettaa arvoja vain ruutuihin, ja j‰tt‰‰ reunat ja kulmat huomioitta.
 - Luokka `Partition` sis‰lt‰‰ tiedon siit‰, miten pulmaruudukko on jaettu alueisiin. Luokka `Region`esitt‰‰ yht‰ t‰llaista aluetta, ja se on k‰yt‰nnˆss‰ vain lista luokan `CellCoordinate` olioita, jotka esitt‰v‰t ruutujen sijaintia ruudukossa (mutta eiv‰t sis‰ll‰ itse ruudun arvoa). Kaikki pulmatyylit eiv‰t v‰ltt‰m‰tt‰ k‰yt‰ alueita, jolloin `Partition` j‰‰ `null`:ksi.
 - Lista `clues` sis‰lt‰‰ pulman annetut vihjeet, jotka ovat `Clue`-olioita. Kaikki pulmatyylit, kuten Sudoku, eiv‰t k‰yt‰ vihjeit‰, jolloin lista j‰‰ tyhj‰ksi. (`Clue`-luokkaa ei ole viel‰ toteutettu, sill‰ niit‰ tarvitsevia pulmia ei ole viel‰ toteutettu.)
 - Lista `values` sis‰lt‰‰ kaikki arvot, mit‰ pulmaan voi syˆtt‰‰. Arvot ovat `Ïnt`-muotoisia, ja jokainen pulmaluokka saa p‰‰tt‰‰ itse, miten se luvut tulkitsee, mutta `Grid`-luokka sis‰lt‰‰ nimettyj‰ vakioita, jotka esitt‰v‰t yleisi‰ arvoja.
 - Lista `givens` sis‰lt‰‰ pulmaan valmiiksi t‰ytettyjen ruutujen koordinaatit (esimerkiksi Sudokussa ruudukkoon valmiiksi t‰ytetyt luvut). Koska kyseess‰ on vain lista `CellCoordinate`-olioita, lista ei tied‰ itse t‰ytetyist‰ arvoista mit‰‰n.

Ohjelman toiminnallisuuden syd‰n on `Solver`-luokka, joka etsii annetun pulman ratkaisut backtracking-algoritmilla. `Puzzle`-luokka tarjoaa ratkojalle metodeja, joilla se muunmuassa testaa, ett‰ pulma on ratkottavissa eik‰ ristiriitainen, ja jokaisen konkreettisen pulmaluokan on toteutettava n‰m‰ metodit. (Suunnitelmana on muuttaa rakennetta niin, ett‰ `Puzzle`-luokka tuntee ratkojansa, jolloin on mahdollista tehd‰ `Solver`-luokasta abstrakti, jolloin pulmat voivat k‰ytt‰‰ itselleen optimoituja ratkojia.)

Pulmaluokat k‰ytt‰v‰t myˆs staattisia metodeja kokoavia apuluokkia: `IsDuplicated` tarjoaa pulmiatyylien kuten Sudokun tarpeisiin metodeja testata mm. sit‰, esiintyykˆ arvo rivill‰ useaan kertaan; `Neighbours` tarjoaa metodeja ruudun naapuriruutujen hakemiseen, ja `SingleTimeEntryQueue` on floodfill-algoritmia varten oleva jono, joka ottaa sis‰‰ns‰ alkion vain kerran.

Jokaisen pulmaluokan on myˆs toteutettava `deepCopy`-metodi, jolla pulma kopioidaan. T‰t‰ ei voi toteuttaa abstraktissa `Puzzle`-luokassa siksi, ett‰ palautettava pulma olisi oikean aliluokan tyyppinen, ja siksi, ett‰ konkreettiset pulmat saattavat pit‰‰ sis‰ll‰‰n lis‰rakennetta, josta `Puzzle` ei tied‰ lainkaan.

#### Pulmanpiirtoluokat

Pulmanpiirtoluokat lˆytyv‰t [luokkakaaviosta](/dokumentaatio/luokkakaavio.png) keskelt‰, `GridPane`-luokasta l‰htien oikealle `Grid`-luokkaan asti.

N‰iden luokkien syd‰n on `JPanel`-luokkaa jatkava `GridPane`-luokka, johon itse pulmaruudukko varsinaisesti piirret‰‰n. Piirtotyˆn hoitaa abstraktin `Drawer`-luokan toteuttava luokka.

`Drawer`-luokka sis‰lt‰‰ itse pulman ja vakioita kuten ruudukon ruudun leveys sek‰ pulmatyylin vaatimat sivumarginaalit (ruudukon ulkopuolisille vihjeille). Lis‰ksi se tarjoaa apumetodeja kuten ruudukkokoordinaatin muuntaminen pikselikoordinaatiksi, sek‰ abstraktit piirtometodit joita `GridPane` kutsuu piirt‰‰kseen pulman.

Jokaiselle pulmalle on tarkoitus luoda samalla myˆs sen oma piirtoluokka joka toteuttaa abstraktit piirtometodit siten, kuin pulma on tarkoitus piirt‰‰.

Jokaisen piirtoluokan on myˆs toteutettava `deepCopy`-metodi, jolla tuotetaan toinen samanlainen piirt‰j‰. T‰t‰ ei voi toteuttaa `Drawer`-luokassa siksi, ett‰ sek‰ palautettava piirt‰j‰ sek‰ sen sis‰lt‰m‰ pulma olisivat oikean aliluokan tyyppisi‰ (kun `Drawer` kopioidaan, kopioidaan myˆs sen sis‰lt‰m‰ `Puzzle`. Suunnitelmana on refaktoroida siten, ett‰ piirt‰j‰luokan ei tarvitse tiet‰‰, mink‰ tyyppist‰ `Puzzle`-luokkaa se piirt‰‰, joka v‰hent‰‰ riippuvuutta.)

`Editor`-luokka sis‰lt‰‰ hiiren tapahtumakuuntelijan, ja vastaa pulman muokkauksesta. Jotta `GridPane`-luokka tiet‰isi p‰ivitty‰, kun pulmaa muutetaan, t‰ytyy `Editor`-olion tuntea `GridPane`-olionsa, jotta se voi kertoa sille p‰ivityksist‰.

En saanut hiirenrullakuuntelijaa toimimaan Editor-luokassa. Se silloin kiinnittyisi `GridPane`-luokkaan, mutta ilmeisesti se vaatisi fokuksen, jotta se huomaisi hiiren rullan liikkeet. Tein rumasti kiinnitt‰m‰ll‰ hiirenrullakuuntelijan itse pulmanmuokkausikkunaan, joka v‰litt‰‰ tiedon eteenp‰in aktiivisen `GridPane`-olion editorille.

#### Ohjelmaikkunaluokat

Pulmaluokat lˆytyv‰t [luokkakaaviosta](/dokumentaatio/luokkakaavio.png) vasemmalta puolelta, kolmen allekkaisen luokan ryhm‰.

Ohjelman p‰‰ikkuna on `MainWindow`-luokan olio, josta valitaan pulman tyyli sek‰ koko, ja luodaan itse pulmanmuokkausikkunat.

Luokka `PuzzleEditorWindow` sis‰lt‰‰ `JTabbedPane`-olion, joka sis‰lt‰‰ `GridPane`-oliot jotka sis‰lt‰v‰t yksitt‰iset pulmat. Lis‰ksi siin‰ luodaan `JMenuBar`-olio, joka on yl‰rivin valikko.

Clean code-mieless‰ luokassa `PuzzleEditorWindow` on eniten siistitt‰v‰‰.
