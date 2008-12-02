/**
 * Copyright 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.qrcode.encoder;

import com.google.zxing.common.ByteMatrix;
import com.google.zxing.WriterException;
import junit.framework.TestCase;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author mysen@google.com (Chris Mysen) - ported from C++
 */
public final class QRCodeTestCase extends TestCase {
  public void test() {
    QRCode qrCode = new QRCode();
    // Initially the QR Code should be invalid.
    assertFalse(qrCode.isValid());

    // First, test simple setters and getters.
    // We use numbers of version 7-H.
    qrCode.setMode(QRCode.MODE_8BIT_BYTE);
    qrCode.setECLevel(QRCode.EC_LEVEL_H);
    qrCode.setVersion(7);
    qrCode.setMatrixWidth(45);
    qrCode.setMaskPattern(3);
    qrCode.setNumTotalBytes(196);
    qrCode.setNumDataBytes(66);
    qrCode.setNumECBytes(130);
    qrCode.setNumRSBlocks(5);

    assertEquals(QRCode.MODE_8BIT_BYTE, qrCode.getMode());
    assertEquals(QRCode.EC_LEVEL_H, qrCode.getECLevel());
    assertEquals(7, qrCode.getVersion());
    assertEquals(45, qrCode.getMatrixWidth());
    assertEquals(3, qrCode.getMaskPattern());
    assertEquals(196, qrCode.getNumTotalBytes());
    assertEquals(66, qrCode.getNumDataBytes());
    assertEquals(130, qrCode.getNumECBytes());
    assertEquals(5, qrCode.getNumRSBlocks());

    // It still should be invalid.
    assertFalse(qrCode.isValid());

    // Prepare the matrix.
    ByteMatrix matrix = new ByteMatrix(45, 45);
    // Just set bogus zero/one values.
    for (int y = 0; y < 45; ++y) {
      for (int x = 0; x < 45; ++x) {
        matrix.set(y, x, (y + x) % 2);
      }
    }

    // Set the matrix.
    qrCode.setMatrix(matrix);
    assertEquals(matrix, qrCode.getMatrix());

    // Finally, it should be valid.
    assertTrue(qrCode.isValid());

    // Make sure "at()" returns the same value.
    for (int y = 0; y < 45; ++y) {
      for (int x = 0; x < 45; ++x) {
        assertEquals((y + x) % 2, qrCode.at(x, y));
      }
    }
  }

  public void testToString() {
    {
      QRCode qrCode = new QRCode();
      String expected =
	"<<\n" +
	" mode: UNDEFINED\n" +
	" ecLevel: UNDEFINED\n" +
	" version: -1\n" +
	" matrixWidth: -1\n" +
	" maskPattern: -1\n" +
	" numTotalBytes: -1\n" +
	" numDataBytes: -1\n" +
	" numECBytes: -1\n" +
	" numRSBlocks: -1\n" +
	" matrix: null\n" +
	">>\n";
      assertEquals(expected, qrCode.toString());
    }
    {
      String expected =
	"<<\n" +
	" mode: 8BIT_BYTE\n" +
	" ecLevel: H\n" +
	" version: 1\n" +
	" matrixWidth: 21\n" +
	" maskPattern: 3\n" +
	" numTotalBytes: 26\n" +
	" numDataBytes: 9\n" +
	" numECBytes: 17\n" +
	" numRSBlocks: 1\n" +
	" matrix:\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	" 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1\n" +
	" 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0\n" +
	">>\n";
      QRCode qrCode = new QRCode();
      qrCode.setMode(QRCode.MODE_8BIT_BYTE);
      qrCode.setECLevel(QRCode.EC_LEVEL_H);
      qrCode.setVersion(1);
      qrCode.setMatrixWidth(21);
      qrCode.setMaskPattern(3);
      qrCode.setNumTotalBytes(26);
      qrCode.setNumDataBytes(9);
      qrCode.setNumECBytes(17);
      qrCode.setNumRSBlocks(1);
      ByteMatrix matrix = new ByteMatrix(21, 21);
      for (int y = 0; y < 21; ++y) {
        for (int x = 0; x < 21; ++x) {
          matrix.set(y, x, (y + x) % 2);
        }
      }
      qrCode.setMatrix(matrix);
      assertTrue(qrCode.isValid());
      assertEquals(expected, qrCode.toString());
    }
  }

  public void testIsValidVersion() {
    assertFalse(QRCode.isValidVersion(0));
    assertTrue(QRCode.isValidVersion(1));
    assertTrue(QRCode.isValidVersion(40));
    assertFalse(QRCode.isValidVersion(0));
  }

  public void testIsValidECLevel() {
    assertFalse(QRCode.isValidECLevel(QRCode.EC_LEVEL_UNDEFINED));
    assertTrue(QRCode.isValidECLevel(QRCode.EC_LEVEL_L));
    assertTrue(QRCode.isValidECLevel(QRCode.EC_LEVEL_Q));
    assertTrue(QRCode.isValidECLevel(QRCode.EC_LEVEL_M));
    assertTrue(QRCode.isValidECLevel(QRCode.EC_LEVEL_H));
    assertFalse(QRCode.isValidECLevel(QRCode.NUM_EC_LEVELS));
  }

  public void testIsValidMode() {
    assertFalse(QRCode.isValidMode(QRCode.MODE_UNDEFINED));
    assertTrue(QRCode.isValidMode(QRCode.MODE_NUMERIC));
    assertTrue(QRCode.isValidMode(QRCode.MODE_ALPHANUMERIC));
    assertTrue(QRCode.isValidMode(QRCode.MODE_8BIT_BYTE));
    assertFalse(QRCode.isValidMode(QRCode.NUM_MODES));
  }

  public void testIsValidMatrixWidth() {
    assertFalse(QRCode.isValidMatrixWidth(20));
    assertTrue(QRCode.isValidMatrixWidth(21));
    assertTrue(QRCode.isValidMatrixWidth(177));
    assertFalse(QRCode.isValidMatrixWidth(178));
  }

  public void testIsValidMaskPattern() {
    assertFalse(QRCode.isValidMaskPattern(-1));
    assertTrue(QRCode.isValidMaskPattern(0));
    assertTrue(QRCode.isValidMaskPattern(7));
    assertFalse(QRCode.isValidMaskPattern(8));
  }

  public void testModeToString() {
    assertEquals("UNDEFINED", QRCode.modeToString(QRCode.MODE_UNDEFINED));
    assertEquals("NUMERIC", QRCode.modeToString(QRCode.MODE_NUMERIC));
    assertEquals("ALPHANUMERIC", QRCode.modeToString(QRCode.MODE_ALPHANUMERIC));
    assertEquals("8BIT_BYTE", QRCode.modeToString(QRCode.MODE_8BIT_BYTE));
    assertEquals("UNKNOWN", QRCode.modeToString(QRCode.NUM_MODES));
  }

  public void testECLevelToString() {
    assertEquals("UNDEFINED", QRCode.ecLevelToString(QRCode.EC_LEVEL_UNDEFINED));
    assertEquals("L", QRCode.ecLevelToString(QRCode.EC_LEVEL_L));
    assertEquals("M", QRCode.ecLevelToString(QRCode.EC_LEVEL_M));
    assertEquals("Q", QRCode.ecLevelToString(QRCode.EC_LEVEL_Q));
    assertEquals("H", QRCode.ecLevelToString(QRCode.EC_LEVEL_H));
    assertEquals("UNKNOWN", QRCode.ecLevelToString(QRCode.NUM_EC_LEVELS));
  }

  public void testGetModeCode() throws WriterException {
    assertEquals(1, QRCode.getModeCode(QRCode.MODE_NUMERIC));
    assertEquals(2, QRCode.getModeCode(QRCode.MODE_ALPHANUMERIC));
    assertEquals(4, QRCode.getModeCode(QRCode.MODE_8BIT_BYTE));
    assertEquals(8, QRCode.getModeCode(QRCode.MODE_KANJI));
    try {
      QRCode.getModeCode(QRCode.MODE_UNDEFINED);
      fail("Should have thrown exception");      
    } catch (WriterException we) {
      // good
    }
  }

  public void testGetECLevelCode() throws WriterException {
    assertEquals(1, QRCode.getECLevelCode(QRCode.EC_LEVEL_L));
    assertEquals(0, QRCode.getECLevelCode(QRCode.EC_LEVEL_M));
    assertEquals(3, QRCode.getECLevelCode(QRCode.EC_LEVEL_Q));
    assertEquals(2, QRCode.getECLevelCode(QRCode.EC_LEVEL_H));
    try {
      QRCode.getECLevelCode(QRCode.EC_LEVEL_UNDEFINED);
      fail("Should have thrown exception");
    } catch (WriterException we) {
      // good
    }
  }
}
