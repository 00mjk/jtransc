/*
 * Copyright 2016 Carlos Ballesteros Velasco
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

package java.lang;

import com.jtransc.annotation.JTranscKeep;
import com.jtransc.annotation.JTranscMethodBody;
import com.jtransc.annotation.JTranscSync;

import com.jtransc.text.JTranscStringTools;

public final class Float extends Number implements Comparable<Float> {
	public static final float POSITIVE_INFINITY = 1.0f / 0.0f;
	public static final float NEGATIVE_INFINITY = -1.0f / 0.0f;
	public static final float NaN = 0.0f / 0.0f;
	public static final float MAX_VALUE = 0x1.fffffeP+127f; // 3.4028235e+38f
	public static final float MIN_NORMAL = 0x1.0p-126f; // 1.17549435E-38f
	public static final float MIN_VALUE = 0x0.000002P-126f; // 1.4e-45f
	public static final int MAX_EXPONENT = 127;
	public static final int MIN_EXPONENT = -126;
	public static final int SIZE = 32;
	public static final int BYTES = SIZE / Byte.SIZE;
	public static final Class<Float> TYPE = (Class<Float>) Class.getPrimitiveClass("float");

	private final float value;

	@JTranscSync
	public static float parseFloat(String value) throws NumberFormatException {
		return (float)Double.parseDouble(value);
	}

	@JTranscSync
	public static String toString(float v) {
		return JTranscStringTools.toString(v);
	}

	// @TODO: CHECK!
	@JTranscSync
	public static String toHexString(float value) {
		return Double.toHexString(value);
	}

	@JTranscSync
	public static Float valueOf(String value) throws NumberFormatException {
		return valueOf(parseFloat(value));
	}

	@JTranscKeep
	@JTranscSync
	public static Float valueOf(float value) {
		return new Float(value);
	}

	@JTranscMethodBody(target = "cpp", value = "return std::isnan(p0);")
	@JTranscMethodBody(target = "d", value = "return std.math.isNaN(p0);")
	@JTranscMethodBody(target = "cs", value = "return Single.IsNaN(p0);")
	@JTranscSync
	public static boolean isNaN(float value) {
		return Double.isNaN((double)value);
	}

	@JTranscMethodBody(target = "cpp", value = "return std::isfinite(p0);")
	@JTranscMethodBody(target = "d", value = "return to!bool(std.math.isFinite(p0));")
	@JTranscMethodBody(target = "cs", value = "return !float.IsNaN(p0) && !float.IsInfinity(p0);")
	@JTranscSync
	private static boolean _isFinite(float v) {
		return Double.isFinite(v);
	}

	@JTranscSync
	public static boolean isInfinite(float v) {
		return !isNaN(v) && !_isFinite(v);
	}

	@JTranscSync
	public static boolean isFinite(float d) {
		return _isFinite(d);
	}

	@JTranscSync
	public Float(float value) {
		this.value = value;
	}

	@JTranscSync
	public Float(double value) {
		this.value = (float) value;
	}

	@JTranscSync
	public Float(String s) throws NumberFormatException {
		value = parseFloat(s);
	}

	@JTranscSync
	public boolean isNaN() {
		return isNaN(value);
	}

	@JTranscSync
	public boolean isInfinite() {
		return isInfinite(value);
	}

	@JTranscSync
	public String toString() {
		return toString(value);
	}

	@JTranscSync
	public byte byteValue() {
		return (byte) value;
	}

	@JTranscSync
	public short shortValue() {
		return (short) value;
	}

	@JTranscSync
	public int intValue() {
		return (int) value;
	}

	@JTranscSync
	public long longValue() {
		return (long) value;
	}

	@JTranscSync
	public float floatValue() {
		return value;
	}

	@JTranscSync
	public double doubleValue() {
		return (double) value;
	}

	@Override
	@JTranscSync
	public int hashCode() {
		return Float.hashCode(value);
	}

	@JTranscSync
	public static int hashCode(float value) {
		return floatToIntBits(value);
	}

	@JTranscSync
	public boolean equals(Object obj) {
		return (obj instanceof Float) && (floatToIntBits(((Float) obj).value) == floatToIntBits(value));
	}


	@JTranscMethodBody(target = "js", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "cpp", value = "return *(int *)&p0;")
	@JTranscMethodBody(target = "d", value = "return *cast(int *)&p0;")
	@JTranscMethodBody(target = "cs", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "as3", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "dart", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "php", value = "return N::floatToIntBits($p0);")
	@JTranscSync
	native public static int floatToIntBits(float value);


	@JTranscMethodBody(target = "js", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "cpp", value = "return *(int *)&p0;")
	@JTranscMethodBody(target = "d", value = "return *cast(int *)&p0;")
	@JTranscMethodBody(target = "cs", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "as3", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "dart", value = "return N.floatToIntBits(p0);")
	@JTranscMethodBody(target = "php", value = "return N::floatToIntBits($p0);")
	@JTranscSync
	native public static int floatToRawIntBits(float value);


	@JTranscMethodBody(target = "js", value = "return N.intBitsToFloat(p0);")
	@JTranscMethodBody(target = "cpp", value = "return *(float *)&p0;")
	@JTranscMethodBody(target = "d", value = "return *cast(float *)&p0;")
	@JTranscMethodBody(target = "cs", value = "return N.intBitsToFloat(p0);")
	@JTranscMethodBody(target = "as3", value = "return N.intBitsToFloat(p0);")
	@JTranscMethodBody(target = "dart", value = "return N.intBitsToFloat(p0);")
	@JTranscMethodBody(target = "php", value = "return N::intBitsToFloat($p0);")
	@JTranscSync
	native public static float intBitsToFloat(int bits);

	@JTranscSync
	public int compareTo(Float that) {
		return compare(this.value, that.value);
	}

	@JTranscSync
	public static int compare(float f1, float f2) {
		if (f1 < f2) return -1;
		if (f1 > f2) return 1;
		int b1 = Float.floatToIntBits(f1);
		int b2 = Float.floatToIntBits(f2);
		return (b1 == b2 ? 0 : (b1 < b2 ? -1 : 1));
	}

	@JTranscSync
	public static float sum(float l, float r) {
		return l + r;
	}

	@JTranscSync
	public static float max(float l, float r) {
		return Math.max(l, r);
	}

	@JTranscSync
	public static float min(float l, float r) {
		return Math.min(l, r);
	}
}
