# K‰yttˆohje

Ohjelman k‰ynnistyess‰ ruudulle ilmestyy Puzzlecrafter-niminen ikkuna, joka on ohjelman p‰‰ikkuna. Sen sulkemalla ohjelma p‰‰ttyy.

## P‰‰ikkuna:

P‰‰ikkunan kautta luodaan uusia pulmaikkunoita. Siin‰ on kaksi osaa:

 - dropdown-valikko, josta valitaan pulmatyyli (kurssin p‰‰ttyess‰ vain Sudoku tai Fillomino)
 - pulman asetukset, jotka muuttuvat pulmatyylin myˆt‰.

### Fillominon asetukset:
 - Grid height: m‰‰r‰‰ pulmaruudukon korkeuden ruuduissa. Korkeus on v‰lilt‰ 3-100.
 - Grid width: m‰‰r‰‰ pulmaruudukon leveyden ruuduissa. Leveys on v‰lilt‰ 3-100.
 - Cell size: m‰‰r‰‰ pulmaruudukon ruudun korkeuden ja leveyden pikseleiss‰. Koko on v‰lilt‰ 25-100.

### Sudokun asetukset:
 - Grid size: m‰‰r‰‰ pulmaruudukon korkeuden ja leveyden ruuduissa. Koko on v‰lilt‰ 3-100.
 - Region width: Sudokuruudukko jaetaan samankokoisiin suorakulmaisiin alueisiin, t‰m‰ asetus m‰‰r‰‰ yhden suorakulmion leveyden. Jotta leveys olisi mielek‰s, tulee sen jakaa koko pulmaruudukon koko (ohjelma antaa virheviestin, mik‰li n‰in ei ole).
 - Cell size: m‰‰r‰‰ pulmaruudukon ruudun korkeuden ja leveyden pikseleiss‰. Koko on v‰lilt‰ 25-100.

Kyseisi‰ asetuksia ei voi muuttaa pulmaikkunasta, vaan niiden muuttamiseksi on luotava uusi pulmaikkuna.

## Pulmaikkuna:

Pulmaikkunan kautta laaditaan ja ratkotaan pulmia. Pulmaikkunan sulkeminen ei pys‰yt‰ muun ohjelman toimintaa.

**Huomio: ohjelma vaatii rullahiiren toimiakseen.**

### Pulman muokkaaminen

Pulman muokkaaminen suoritetaan t‰ysin hiiren kanssa.

#### Ruudun t‰ytt‰minen

Aktiivinen ruutu valitaan klikkaamalla sit‰ hiiren vasemmalla napilla, jolloin se korostuu harmaalla v‰rill‰. Aktiiviseen ruutuun valitaan arvo pyˆritt‰m‰ll‰ hiiren rullaa ylˆs ja alas.

#### Ruudun lukitus

Klikkaamalla ruutua hiiren oikealla napilla sen arvo lukitaan, mik‰ n‰kyy harmaana ympyr‰n‰ ruudun takana. Klikkaamalla lukittua ruutua hiiren oikealla napilla uudestaan lukitus poistuu. 

Lukitus ei vaadi kursoria lukittavassa ruudussa, eik‰ se myˆsk‰‰n siirr‰ kursoria.

Lukitun ruudun arvoa ei voi muuttaa, vaikka se olisikin kursorin alla, vaan lukitus t‰ytyy ensin poistaa. Tyhj‰‰ ruutua ei voi lukita.

### Yl‰palkkivalikko:

Yl‰palkin kautta voi muokata v‰lilehti‰ sek‰ ratkoa aktiivisen v‰lilehden pulman.

#### Tab-valikko:

- Close: sulkee nykyisen v‰lilehden.
- Rename: uudelleennime‰‰ nykyisen v‰lilehden. Tyhj‰‰ nime‰ ei sallita, vaan vanha nimi j‰‰ ennalleen.
- Copy: kopioi nykyisen v‰lilehden. Kopiot nimet‰‰n laittamalla vanhan v‰lilehden nimen per‰‰n piste ja kopion numero.

#### Solve-valikko:

**Huomio: Ratkoja ottaa huomioon vain lukitut ruudut: lukitsemattomista ruuduista se pyyhkii arvot pois.**

- Count solutions: laskee nykyisen v‰lilehden pulman ratkaisujen m‰‰r‰n.
- Generate solutions: ratkoo pulman ja avaa ratkaisut uusiin v‰lilehtiin. Ratkaisut nimet‰‰n laittamalla vanhan v‰lilehden nimen per‰‰n .s. ja ratkaisun numero.

Sudokun ratkominen ainakin korkeintaan 9x9-kokoisena pit‰isi sujua sekunneissa, kunhan pulmassa on riitt‰v‰sti vihjeit‰. Fillominon ratkomisessa jo 6x6-koossa voi kulua jopa muutama minuutti.

Mik‰li et ole varma pulman ratkaisujen m‰‰r‰st‰, on hyv‰ ensin laskea ratkaisujen m‰‰r‰. T‰ll‰ tavalla v‰ltyt‰‰n silt‰, ett‰ ratkoja avaa satoja v‰lilehti‰ sadoille ratkaisuille.
