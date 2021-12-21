/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.xml;

import io.github.astrapi69.crypto.key.reader.PrivateKeyReader;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class XmlableTest {

    File pemDir;
    File privateKeyPemFile;

    @Test
    public void testToXml() throws NoSuchAlgorithmException, NoSuchProviderException {

        Security.addProvider(new BouncyCastleProvider());
        String actual;
        String expected;
        PrivateKey privateKey;


        pemDir = new File(PathFinder.getSrcTestResourcesDir(), "pem");
        privateKeyPemFile = new File(pemDir, "private.pem");
        privateKey = RuntimeExceptionDecorator
                .decorate(() -> PrivateKeyReader.readPemPrivateKey(privateKeyPemFile));

        TestBox testBox = new TestBox(privateKey);
        actual = testBox.toXml();
        expected = "<testbox>\n" +
                "  <privateKey class=\"org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateCrtKey\" serialization=\"custom\">\n" +
                "    <org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateKey>\n" +
                "      <default>\n" +
                "        <algorithmIdentifierEnc>MA0GCSqGSIb3DQEBAQUA</algorithmIdentifierEnc>\n" +
                "        <modulus>28101262299496173449583607950653288236615236883375436571872738630466294361850594782734771781238265882740317165071571832220290144918921957733504531027743756163340396511372870336063546077518126360152045829414503008274796520156167685746168726298336977141006327279965197906875244309450553957061663137464402269290234501215202323598873787394189769301995043754859689289346402257512055824844462864535099713273198789942071144812056760018514876429503505369079369174717854736326187746548213741575945121437193027293252111789726471521673530771451996462724499113976979848412364358824221849216681898358858072894983093733025242375777</modulus>\n" +
                "        <privateExponent>2987771727465238515597274519739263506610540162097136610354597292782537179202205539559270179771247336175512000949367723986617967404596612623206121308593901047441229883748816096276771733038532500382065937399640767225518137120224856711160164256020447330798359529529845720968410246856759692582902310784014450042144318116686990612634288746160585816637345625795393720986894027102346950274859734128345922443140251761031406938354161251476576079444122974677692202708730065196473105175699827078429725528125412408024840214375786464582201223582664321965424458271993483653687764944161267658888864330333000515809059151428653898977</privateExponent>\n" +
                "      </default>\n" +
                "    </org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateKey>\n" +
                "    <org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateCrtKey>\n" +
                "      <default>\n" +
                "        <crtCoefficient>72922507901434074286439875314005814469976365255912451682505889663891893316987040274883329007887940899089515565247845538313758897445086319690691056459294179086518757867683844238624017123898999134308033579749420222227046895534132908316227716130218981327286042562633306578795995246548824420225612483317443366970</crtCoefficient>\n" +
                "        <primeExponentP>77623431744865183257478014178896664871320760534428892333398626129431435508390412332403081625312505441722494574541388228945244255683533393944272100779847003105681202475292339941596711628201045735609392340456856191873314799792174563967122424883580641439981909555160261112235108811844973917464299094893420023649</primeExponentP>\n" +
                "        <primeExponentQ>57227098965888801248323661642965045468924537310002414299362124475044809990267948271969575735041598587768186450376864800739156501289590264588166973245780675865993158543613637383033581781301958329923519654818865738450345972366610014779500437695177138112926278665064562479571349309873588227411487360802745840761</primeExponentQ>\n" +
                "        <primeP>179734555054523371790041570634622340505644032050058872133053482216101893333570606735044543544308425280319641285038120419742243950845524591539208616054580025527735619227785262957618134891796634199216815496626659986108021199617712846195354096933126925454073431547362140775598937471802008748899723352954708454279</primeP>\n" +
                "        <primeQ>156348690383835849900424704564573961351380165152810914871489726184801221958153682086879693386168969761821062005934158264383946124104380405632595419610168757471635552420910745088038679723244390656503156062108721189795744705310593652601472410589829252147192326366197108188413686831798622296976265097754275227863</primeQ>\n" +
                "        <publicExponent>65537</publicExponent>\n" +
                "      </default>\n" +
                "    </org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateCrtKey>\n" +
                "  </privateKey>\n" +
                "</testbox>";
        assertNotNull(actual);
        assertEquals(actual, expected);
    }
}
