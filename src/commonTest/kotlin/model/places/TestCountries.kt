package model.places

import com.algolia.search.model.places.Country.*
import com.algolia.search.serialize.*
import shouldEqual
import kotlin.test.Test


internal class TestCountries {

    @Test
    fun raw() {
        Afghanistan.raw shouldEqual KeyAfghanistan
        AlandIslands.raw shouldEqual KeyAlandIslands
        Albania.raw shouldEqual KeyAlbania
        Algeria.raw shouldEqual KeyAlgeria
        AmericanSamoa.raw shouldEqual KeyAmericanSamoa
        Andorra.raw shouldEqual KeyAndorra
        Angola.raw shouldEqual KeyAngola
        Anguilla.raw shouldEqual KeyAnguilla
        Antarctica.raw shouldEqual KeyAntarctica
        AntiguaAndBarbuda.raw shouldEqual KeyAntiguaAndBarbuda
        Argentina.raw shouldEqual KeyArgentina
        Armenia.raw shouldEqual KeyArmenia
        Aruba.raw shouldEqual KeyAruba
        Australia.raw shouldEqual KeyAustralia
        Austria.raw shouldEqual KeyAustria
        Azerbaijan.raw shouldEqual KeyAzerbaijan
        Bahamas.raw shouldEqual KeyBahamas
        Bahrain.raw shouldEqual KeyBahrain
        Bangladesh.raw shouldEqual KeyBangladesh
        Barbados.raw shouldEqual KeyBarbados
        Belarus.raw shouldEqual KeyBelarus
        Belgium.raw shouldEqual KeyBelgium
        Belize.raw shouldEqual KeyBelize
        Benin.raw shouldEqual KeyBenin
        Bermuda.raw shouldEqual KeyBermuda
        Bhutan.raw shouldEqual KeyBhutan
        Bolivia.raw shouldEqual KeyBolivia
        CaribbeanNetherlands.raw shouldEqual KeyCaribbeanNetherlands
        BosniaAndHerzegovina.raw shouldEqual KeyBosniaAndHerzegovina
        Botswana.raw shouldEqual KeyBotswana
        BouvetIsland.raw shouldEqual KeyBouvetIsland
        Brazil.raw shouldEqual KeyBrazil
        BritishIndianOceanTerritory.raw shouldEqual KeyBritishIndianOceanTerritory
        BruneiDarussalam.raw shouldEqual KeyBruneiDarussalam
        Bulgaria.raw shouldEqual KeyBulgaria
        BurkinaFaso.raw shouldEqual KeyBurkinaFaso
        Burundi.raw shouldEqual KeyBurundi
        CaboVerde.raw shouldEqual KeyCaboVerde
        Cambodia.raw shouldEqual KeyCambodia
        Cameroon.raw shouldEqual KeyCameroon
        Canada.raw shouldEqual KeyCanada
        CaymanIslands.raw shouldEqual KeyCaymanIslands
        CentralAfricanRepublic.raw shouldEqual KeyCentralAfricanRepublic
        Chad.raw shouldEqual KeyChad
        Chile.raw shouldEqual KeyChile
        China.raw shouldEqual KeyChina
        ChristmasIsland.raw shouldEqual KeyChristmasIsland
        CocosIslands.raw shouldEqual KeyCocosIslands
        Colombia.raw shouldEqual KeyColombia
        Comoros.raw shouldEqual KeyComoros
        RepublicOfTheCongo.raw shouldEqual KeyRepublicOfTheCongo
        DemocraticRepublicOfTheCongo.raw shouldEqual KeyDemocraticRepublicOfTheCongo
        CookIslands.raw shouldEqual KeyCookIslands
        CostaRica.raw shouldEqual KeyCostaRica
        IvoryCoast.raw shouldEqual KeyIvoryCoast
        Croatia.raw shouldEqual KeyCroatia
        Cuba.raw shouldEqual KeyCuba
        Curacao.raw shouldEqual KeyCuracao
        Cyprus.raw shouldEqual KeyCyprus
        CzechRepublic.raw shouldEqual KeyCzechRepublic
        Denmark.raw shouldEqual KeyDenmark
        Djibouti.raw shouldEqual KeyDjibouti
        Dominica.raw shouldEqual KeyDominica
        DominicanRepublic.raw shouldEqual KeyDominicanRepublic
        Ecuador.raw shouldEqual KeyEcuador
        Egypt.raw shouldEqual KeyEgypt
        ElSalvador.raw shouldEqual KeyElSalvador
        EquatorialGuinea.raw shouldEqual KeyEquatorialGuinea
        Eritrea.raw shouldEqual KeyEritrea
        Estonia.raw shouldEqual KeyEstonia
        Eswatini.raw shouldEqual KeyEswatini
        Ethiopia.raw shouldEqual KeyEthiopia
        FalklandIslands.raw shouldEqual KeyFalklandIslands
        FaroeIslands.raw shouldEqual KeyFaroeIslands
        Fiji.raw shouldEqual KeyFiji
        Finland.raw shouldEqual KeyFinland
        France.raw shouldEqual KeyFrance
        FrenchGuiana.raw shouldEqual KeyFrenchGuiana
        FrenchPolynesia.raw shouldEqual KeyFrenchPolynesia
        FrenchSouthernAndAntarcticLands.raw shouldEqual KeyFrenchSouthernAndAntarcticLands
        Gabon.raw shouldEqual KeyGabon
        Gambia.raw shouldEqual KeyGambia
        Georgia.raw shouldEqual KeyGeorgia
        Germany.raw shouldEqual KeyGermany
        Ghana.raw shouldEqual KeyGhana
        Gibraltar.raw shouldEqual KeyGibraltar
        Greece.raw shouldEqual KeyGreece
        Greenland.raw shouldEqual KeyGreenland
        Grenada.raw shouldEqual KeyGrenada
        Guadeloupe.raw shouldEqual KeyGuadeloupe
        Guam.raw shouldEqual KeyGuam
        Guatemala.raw shouldEqual KeyGuatemala
        BailiwickOfGuernsey.raw shouldEqual KeyBailiwickOfGuernsey
        Guinea.raw shouldEqual KeyGuinea
        GuineaBissau.raw shouldEqual KeyGuineaBissau
        Guyana.raw shouldEqual KeyGuyana
        Haiti.raw shouldEqual KeyHaiti
        HeardIslandAndMcDonaldIslands.raw shouldEqual KeyHeardIslandAndMcDonaldIslands
        VaticanCity.raw shouldEqual KeyVaticanCity
        Honduras.raw shouldEqual KeyHonduras
        HongKong.raw shouldEqual KeyHongKong
        Hungary.raw shouldEqual KeyHungary
        Iceland.raw shouldEqual KeyIceland
        India.raw shouldEqual KeyIndia
        Indonesia.raw shouldEqual KeyIndonesia
        Iran.raw shouldEqual KeyIran
        Iraq.raw shouldEqual KeyIraq
        Ireland.raw shouldEqual KeyIreland
        IsleOfMan.raw shouldEqual KeyIsleOfMan
        Israel.raw shouldEqual KeyIsrael
        Italy.raw shouldEqual KeyItaly
        Jamaica.raw shouldEqual KeyJamaica
        Japan.raw shouldEqual KeyJapan
        Jersey.raw shouldEqual KeyJersey
        Jordan.raw shouldEqual KeyJordan
        Kazakhstan.raw shouldEqual KeyKazakhstan
        Kenya.raw shouldEqual KeyKenya
        Kiribati.raw shouldEqual KeyKiribati
        NorthKorea.raw shouldEqual KeyNorthKorea
        SouthKorea.raw shouldEqual KeySouthKorea
        Kuwait.raw shouldEqual KeyKuwait
        Kyrgyzstan.raw shouldEqual KeyKyrgyzstan
        Laos.raw shouldEqual KeyLaos
        Latvia.raw shouldEqual KeyLatvia
        Lebanon.raw shouldEqual KeyLebanon
        Lesotho.raw shouldEqual KeyLesotho
        Liberia.raw shouldEqual KeyLiberia
        Libya.raw shouldEqual KeyLibya
        Liechtenstein.raw shouldEqual KeyLiechtenstein
        Lithuania.raw shouldEqual KeyLithuania
        Luxembourg.raw shouldEqual KeyLuxembourg
        Macau.raw shouldEqual KeyMacau
        Madagascar.raw shouldEqual KeyMadagascar
        Malawi.raw shouldEqual KeyMalawi
        Malaysia.raw shouldEqual KeyMalaysia
        Maldives.raw shouldEqual KeyMaldives
        Mali.raw shouldEqual KeyMali
        Malta.raw shouldEqual KeyMalta
        MarshallIslands.raw shouldEqual KeyMarshallIslands
        Martinique.raw shouldEqual KeyMartinique
        Mauritania.raw shouldEqual KeyMauritania
        Mauritius.raw shouldEqual KeyMauritius
        Mayotte.raw shouldEqual KeyMayotte
        Mexico.raw shouldEqual KeyMexico
        Micronesia.raw shouldEqual KeyMicronesia
        Moldova.raw shouldEqual KeyMoldova
        Monaco.raw shouldEqual KeyMonaco
        Mongolia.raw shouldEqual KeyMongolia
        Montenegro.raw shouldEqual KeyMontenegro
        Montserrat.raw shouldEqual KeyMontserrat
        Morocco.raw shouldEqual KeyMorocco
        Mozambique.raw shouldEqual KeyMozambique
        Myanmar.raw shouldEqual KeyMyanmar
        Namibia.raw shouldEqual KeyNamibia
        Nauru.raw shouldEqual KeyNauru
        Nepal.raw shouldEqual KeyNepal
        Netherlands.raw shouldEqual KeyNetherlands
        NewCaledonia.raw shouldEqual KeyNewCaledonia
        NewZealand.raw shouldEqual KeyNewZealand
        Nicaragua.raw shouldEqual KeyNicaragua
        Niger.raw shouldEqual KeyNiger
        Nigeria.raw shouldEqual KeyNigeria
        Niue.raw shouldEqual KeyNiue
        NorfolkIsland.raw shouldEqual KeyNorfolkIsland
        NorthMacedonia.raw shouldEqual KeyNorthMacedonia
        NorthernMarianaIslands.raw shouldEqual KeyNorthernMarianaIslands
        Norway.raw shouldEqual KeyNorway
        Oman.raw shouldEqual KeyOman
        Pakistan.raw shouldEqual KeyPakistan
        Palau.raw shouldEqual KeyPalau
        Palestine.raw shouldEqual KeyPalestine
        Panama.raw shouldEqual KeyPanama
        PapuaNewGuinea.raw shouldEqual KeyPapuaNewGuinea
        Paraguay.raw shouldEqual KeyParaguay
        Peru.raw shouldEqual KeyPeru
        Philippines.raw shouldEqual KeyPhilippines
        PitcairnIslands.raw shouldEqual KeyPitcairnIslands
        Poland.raw shouldEqual KeyPoland
        Portugal.raw shouldEqual KeyPortugal
        PuertoRico.raw shouldEqual KeyPuertoRico
        Qatar.raw shouldEqual KeyQatar
        Reunion.raw shouldEqual KeyReunion
        Romania.raw shouldEqual KeyRomania
        Russia.raw shouldEqual KeyRussia
        Rwanda.raw shouldEqual KeyRwanda
        SaintBarthelemy.raw shouldEqual KeySaintBarthelemy
        SaintHelena.raw shouldEqual KeySaintHelena
        SaintKittsAndNevis.raw shouldEqual KeySaintKittsAndNevis
        SaintLucia.raw shouldEqual KeySaintLucia
        SaintMartin.raw shouldEqual KeySaintMartin
        SaintPierreAndMiquelon.raw shouldEqual KeySaintPierreAndMiquelon
        SaintVincentAndTheGrenadines.raw shouldEqual KeySaintVincentAndTheGrenadines
        Samoa.raw shouldEqual KeySamoa
        SanMarino.raw shouldEqual KeySanMarino
        SaoTomeAndPrincipe.raw shouldEqual KeySaoTomeAndPrincipe
        SaudiArabia.raw shouldEqual KeySaudiArabia
        Senegal.raw shouldEqual KeySenegal
        Serbia.raw shouldEqual KeySerbia
        Seychelles.raw shouldEqual KeySeychelles
        SierraLeone.raw shouldEqual KeySierraLeone
        Singapore.raw shouldEqual KeySingapore
        SintMaarten.raw shouldEqual KeySintMaarten
        Slovakia.raw shouldEqual KeySlovakia
        Slovenia.raw shouldEqual KeySlovenia
        SolomonIslands.raw shouldEqual KeySolomonIslands
        Somalia.raw shouldEqual KeySomalia
        SouthAfrica.raw shouldEqual KeySouthAfrica
        SouthGeorgiaAndTheSouthSandwichIslands.raw shouldEqual KeySouthGeorgiaAndTheSouthSandwichIslands
        SouthSudan.raw shouldEqual KeySouthSudan
        Spain.raw shouldEqual KeySpain
        SriLanka.raw shouldEqual KeySriLanka
        Sudan.raw shouldEqual KeySudan
        Suriname.raw shouldEqual KeySuriname
        SvalbardAndJanMayen.raw shouldEqual KeySvalbardAndJanMayen
        Sweden.raw shouldEqual KeySweden
        Switzerland.raw shouldEqual KeySwitzerland
        Syria.raw shouldEqual KeySyria
        Taiwan.raw shouldEqual KeyTaiwan
        Tajikistan.raw shouldEqual KeyTajikistan
        Tanzania.raw shouldEqual KeyTanzania
        Thailand.raw shouldEqual KeyThailand
        TimorLeste.raw shouldEqual KeyTimorLeste
        Togo.raw shouldEqual KeyTogo
        Tokelau.raw shouldEqual KeyTokelau
        Tonga.raw shouldEqual KeyTonga
        TrinidadAndTobago.raw shouldEqual KeyTrinidadAndTobago
        Tunisia.raw shouldEqual KeyTunisia
        Turkey.raw shouldEqual KeyTurkey
        Turkmenistan.raw shouldEqual KeyTurkmenistan
        TurksAndCaicosIslands.raw shouldEqual KeyTurksAndCaicosIslands
        Tuvalu.raw shouldEqual KeyTuvalu
        Uganda.raw shouldEqual KeyUganda
        Ukraine.raw shouldEqual KeyUkraine
        UnitedArabEmirates.raw shouldEqual KeyUnitedArabEmirates
        UnitedKingdom.raw shouldEqual KeyUnitedKingdom
        UnitedStates.raw shouldEqual KeyUnitedStates
        UnitedStatesMinorOutlyingIslands.raw shouldEqual KeyUnitedStatesMinorOutlyingIslands
        Uruguay.raw shouldEqual KeyUruguay
        Uzbekistan.raw shouldEqual KeyUzbekistan
        Vanuatu.raw shouldEqual KeyVanuatu
        Venezuela.raw shouldEqual KeyVenezuela
        Vietnam.raw shouldEqual KeyVietnam
        VirginIslandsGB.raw shouldEqual KeyVirginIslandsGB
        VirginIslandsUS.raw shouldEqual KeyVirginIslandsUS
        WallisAndFutuna.raw shouldEqual KeyWallisAndFutuna
        WesternSahara.raw shouldEqual KeyWesternSahara
        Yemen.raw shouldEqual KeyYemen
        Zambia.raw shouldEqual KeyZambia
        Zimbabwe.raw shouldEqual KeyZimbabwe
    }
}