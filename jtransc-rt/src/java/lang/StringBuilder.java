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

import com.jtransc.annotation.JTranscAddMembers;
import com.jtransc.annotation.JTranscAsync;
import com.jtransc.annotation.JTranscMethodBody;
import com.jtransc.annotation.JTranscSync;


import com.jtransc.io.JTranscConsole;

import java.lang.jtransc.JTranscStrings;
import java.util.Arrays;

// @TODO: Optimize using arrays
@SuppressWarnings("UnnecessaryCallToStringValueOf")
@JTranscAddMembers(target = "as3", value = "public var _str: String = '';")
@JTranscAddMembers(target = "dart", value = "StringBuffer __buffer = new StringBuffer();")
//@JTranscAddMembers(target = "cpp", value = "std::u16string __buffer;")
public class StringBuilder implements java.io.Serializable, Appendable, CharSequence {
	protected char[] buffer;
	protected int length;

	static private final int EXTRA_CAPACITY = 16;

	@JTranscSync
	public StringBuilder() {
		this(EXTRA_CAPACITY);
	}

	@JTranscMethodBody(target = "js", value = "this._str = ''; return this;")
	@JTranscMethodBody(target = "as3", value = "this._str = ''; return this;")
	@JTranscMethodBody(target = "dart", value = "this.__buffer = new StringBuffer(); return this;")
	//@JTranscMethodBody(target = "cpp", value = "__buffer.reserve(p0); return this;")
	@JTranscSync
	public StringBuilder(int capacity) {
		buffer = new char[capacity];
	}

	@JTranscSync
	public StringBuilder(String str) {
		this(str.length() + EXTRA_CAPACITY);
		append(str);
	}

	@JTranscAsync
	public StringBuilder(CharSequence seq) {
		this(seq.length() + EXTRA_CAPACITY);
		append(seq);
	}


	@JTranscMethodBody(target = "js", value = "return this._str.length;")
	@JTranscMethodBody(target = "as3", value = "return this._str.length;")
	@JTranscMethodBody(target = "dart", value = "return this.__buffer.length;")
	//@JTranscMethodBody(target = "cpp", value = "return __buffer.length();")
	@JTranscSync
	public int length() {
		return length;
	}



	@JTranscMethodBody(target = "js", value = "")
	@JTranscMethodBody(target = "as3", value = "")
	@JTranscMethodBody(target = "dart", value = "")
	//@JTranscMethodBody(target = "cpp", value = "__buffer.shrink_to_fit();")
	@JTranscSync
	public void trimToSize() {
		this.buffer = Arrays.copyOf(buffer, length);
	}


	@JTranscMethodBody(target = "js", value = "this._str += N.istr(p0); return this;")
	@JTranscMethodBody(target = "as3", value = "this._str += N.istr(p0); return this;")
	@JTranscMethodBody(target = "dart", value = "this.__buffer.write((p0 != null) ? N.istr(p0) : 'null'); return this;")
	//@JTranscMethodBody(target = "cpp", value = "if (p0 == nullptr) __buffer.append(u\"null\"); else __buffer.append((const char16_t *)N::getStrDataPtr(p0), (int)N::strLen(p0)); return this;")
	@JTranscSync
	public StringBuilder append(String _str) {
		//JTranscConsole.log("append.String:");
		//JTranscConsole.log(str);
		if (_str == null) _str = "null";
		int strlen = _str.length();
		ensureCapacity(length + strlen);
		System.arraycopy(JTranscStrings.getData(_str), 0, buffer, length, strlen);
		length += strlen;
		return this;
	}

	@JTranscSync
	public StringBuilder append(char str[], int offset, int len) {
		return this.append(new String(str, offset, len));
	}


	@JTranscMethodBody(target = "js", value = "this._str += N.ichar(p0); return this;")
	@JTranscMethodBody(target = "as3", value = "this._str += N.ichar(p0); return this;")
	@JTranscMethodBody(target = "dart", value = "this.__buffer.write(N.ichar(p0)); return this;")
	//@JTranscMethodBody(target = "cpp", value = "__buffer.push_back(p0); return this;")
	@JTranscSync
	public StringBuilder append(char v) {
		//JTranscConsole.log("append.char:");
		//JTranscConsole.log(v);
		ensureCapacity(length + 1);
		buffer[length++] = v;
		return this;
	}


	@JTranscMethodBody(target = "js", value = "this._str += p0; return this;")
	@JTranscMethodBody(target = "as3", value = "this._str += p0; return this;")
	@JTranscMethodBody(target = "dart", value = "this.__buffer.write(p0); return this;")
	//@JTranscMethodBody(target = "cpp", value = "char16_t buffer16[12]; char buffer[12]; itoa(p0, buffer, 10); int len = strlen(buffer); for (int n = 0; n < len; n++) buffer16[n] = buffer[n]; __buffer.append(buffer16, len); return this;")
	@JTranscSync
	public StringBuilder append(int v) {
		//return append(Integer.toString(v));
		ensureCapacity(this.length + 11);
		this.length += IntegerTools.writeInt(this.buffer, this.length, v, 10);
		return this;
	}

	//@Override

	@JTranscMethodBody(target = "js", value = "return this._str.charCodeAt(p0) & 0xFFFF;")
	@JTranscMethodBody(target = "as3", value = "return this._str.charCodeAt(p0) & 0xFFFF;")
	@JTranscMethodBody(target = "dart", value = "return this.__buffer.toString().codeUnitAt(p0) & 0xFFFF;")
	//@JTranscMethodBody(target = "cpp", value = "return __buffer[p0];")
	@JTranscSync
	public char charAt(int index) {
		return buffer[index];
	}


	@JTranscMethodBody(target = "js", value = "return this._str.indexOf(p0._str, p1);")
	@JTranscMethodBody(target = "as3", value = "return this._str.indexOf(N.istr(p0), p1);")
	@JTranscMethodBody(target = "dart", value = "return this.__buffer.toString().indexOf(N.istr(p0), p1);")
	//@JTranscMethodBody(target = "cpp", value = "exit(7777);")
	@JTranscSync
	public int indexOf(String str, int fromIndex) {
		return JTranscStrings.indexOf(buffer, fromIndex, JTranscStrings.getData(str));
	}


	@JTranscMethodBody(target = "js", value = "return this._str.lastIndexOf(N.istr(p0), p1);")
	@JTranscMethodBody(target = "as3", value = "return this._str.lastIndexOf(N.istr(p0), p1);")
	@JTranscMethodBody(target = "dart", value = "return this.__buffer.toString().lastIndexOf(N.istr(p0), p1);")
	//@JTranscMethodBody(target = "cpp", value = "exit(7778);")
	@JTranscSync
	public int lastIndexOf(String str, int fromIndex) {
		return JTranscStrings.lastIndexOf(buffer, fromIndex, JTranscStrings.getData(str));
	}


	@JTranscMethodBody(target = "js", value = "this._str = this._str.reverse(); return this;")
	@JTranscMethodBody(target = "as3", value = "var len: int = this._str.length; var reversed: String = ''; for (var n:int = 0; n < len; n++) reversed += this._str.substr(len - n - 1, 1); this._str = reversed; return this;")
	@JTranscMethodBody(target = "dart", value = "var str = this.__buffer.toString(); int len = str.length; this.__buffer = new StringBuffer(); for (int n = 0; n < len; n++) this.__buffer.write(str[len - n - 1]); return this;")
	//@JTranscMethodBody(target = "cpp", value = "auto len = __buffer.length(); for (int n = 0; n < len; n++) std::swap(__buffer[n], __buffer[len - n - 1]); return this;")
	@JTranscSync
	public StringBuilder reverse() {
		int len = length / 2;
		for (int n = 0; n < len; n++) {
			int m = length - n - 1;
			char temp = this.buffer[n];
			this.buffer[n] = this.buffer[m];
			this.buffer[m] = temp;
		}
		return this;
	}


	@JTranscMethodBody(target = "js", value = "this._str = this._str.substr(0, p0) + this._str.substr(p1); return this;")
	@JTranscMethodBody(target = "as3", value = "this._str = this._str.substr(0, p0) + this._str.substr(p1); return this;")
	@JTranscMethodBody(target = "dart", value = "var str = this.__buffer.toString(); this.__buffer = new StringBuffer()..write(str.substring(0, p0))..write(str.substring(p1)); return this;")
	//@JTranscMethodBody(target = "cpp", value = "__buffer.erase(p0, p1 - p0); return this;")
	@JTranscSync
	public StringBuilder delete(int start, int end) {
		return replace(start, end, "");
	}


	@JTranscMethodBody(target = "js", value = "this._str = this._str.substr(0, p0) + N.istr(p2) + this._str.substr(p1); return this;")
	@JTranscMethodBody(target = "as3", value = "this._str = this._str.substr(0, p0) + N.istr(p2) + this._str.substr(p1); return this;")
	@JTranscMethodBody(target = "dart", value = "var str = this.__buffer.toString(); this.__buffer = new StringBuffer()..write(str.substring(0, p0))..write(N.istr(p2))..write(str.substring(p1)); return this;")
	//@JTranscMethodBody(target = "cpp", value = "exit(7780); return this;")
	@JTranscSync
	public StringBuilder replace(int start, int end, String str) {
		//ensure(end);
		int addLength = str.length();
		int removeLength = end - start;

		ensureCapacity(this.length - removeLength + addLength);

		System.arraycopy(buffer, end, buffer, start + addLength, this.length - end);
		System.arraycopy(JTranscStrings.getData(str), 0, buffer, start, addLength);
		this.length = this.length - removeLength + addLength;
		return this;
	}


	@JTranscMethodBody(target = "js", value = "")
	@JTranscMethodBody(target = "as3", value = "")
	@JTranscMethodBody(target = "dart", value = "")
	//@JTranscMethodBody(target = "cpp", value = "__buffer.reserve(p0);")
	@JTranscSync
	public void ensureCapacity(int minimumCapacity) {
		ensure(minimumCapacity);
	}


	@JTranscMethodBody(target = "js", value = "return N.str(this._str);")
	@JTranscMethodBody(target = "as3", value = "return N.str(this._str);")
	@JTranscMethodBody(target = "dart", value = "return N.str(this.__buffer.toString());")
	//@JTranscMethodBody(target = "cpp", value = "return N::str(__buffer);")
	@JTranscSync
	public String toStringSync() {
		return new String(buffer, 0, length);
	}

	@JTranscSync
	//@JTranscMethodBody(target = "cpp", value = "return __buffer.capacity());")
	public int capacity() {
		return buffer.length;
	}

	/////////////////////////////////////////////////////
	/////////////////////////////////////////////////////
	/////////////////////////////////////////////////////

	@JTranscSync
	public int lastIndexOf(String str) {
		return lastIndexOf(str, length);
	}
	@JTranscSync
	public int indexOf(String str) {
		return indexOf(str, 0);
	}

	@JTranscSync
	private char[] ensure(int minimumCapacity) {
		if (minimumCapacity > buffer.length) {
			buffer = Arrays.copyOf(buffer, Math.max(minimumCapacity, (buffer.length * 2) + 2));
		}
		return buffer;
	}

	@JTranscSync
	public void setLength(int newLength) {
		this.delete(newLength, length());
	}

	@JTranscAsync
	public StringBuilder append(Object obj) {
		return this.append(String.valueOf(obj));
	}

	@JTranscAsync
	public StringBuilder append(StringBuffer sb) {
		return this.append(String.valueOf(sb));
	}

	@JTranscAsync
	public StringBuilder append(CharSequence s) {
		return this.append(String.valueOf(s));
	}

	@JTranscAsync
	public StringBuilder append(CharSequence s, int start, int end) {
		return this.append(s.toString().substring(start, end));
	}

	@JTranscSync
	public StringBuilder append(char[] str) {
		return this.append(str, 0, str.length);
	}

	@JTranscSync
	public StringBuilder append(boolean v) {
		return this.append(String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder append(long v) {
		return this.append(String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder append(float v) {
		return this.append(String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder append(double v) {
		return this.append(String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder appendCodePoint(int codePoint) {
		return this.append(new String(new int[]{codePoint}, 0, 1));
	}

	@JTranscSync
	public StringBuilder deleteCharAt(int index) {
		return this.delete(index, index + 1);
	}

	//@Override
	@JTranscSync
	public CharSequence subSequence(int start, int end) {
		return this.substring(start, end);
	}

	@JTranscSync
	public StringBuilder insert(int offset, String str) {
		return this.replace(offset, offset, str);
	}

	@JTranscSync
	public StringBuilder insert(int offset, char[] str, int pos, int len) {
		return this.insert(offset, String.valueOf(str, pos, len));
	}

	@JTranscAsync
	public StringBuilder insert(int offset, Object obj) {
		return this.insert(offset, String.valueOf(obj));
	}

	@JTranscSync
	public StringBuilder insert(int offset, char[] str) {
		return this.insert(offset, String.valueOf(str));
	}

	@JTranscAsync
	public StringBuilder insert(int offset, CharSequence s) {
		return this.insert(offset, s.toString());
	}

	@JTranscAsync
	public StringBuilder insert(int offset, CharSequence s, int start, int end) {
		return this.insert(offset, s.toString().substring(start, end));
	}

	@JTranscSync
	public StringBuilder insert(int offset, boolean v) {
		return this.insert(offset, String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder insert(int offset, char v) {
		return this.insert(offset, String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder insert(int offset, int v) {
		return this.insert(offset, String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder insert(int offset, long v) {
		return this.insert(offset, String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder insert(int offset, float v) {
		return this.insert(offset, String.valueOf(v));
	}

	@JTranscSync
	public StringBuilder insert(int offset, double v) {
		return this.insert(offset, String.valueOf(v));
	}

	@JTranscSync
	public int codePointAt(int index) {
		return toString().codePointAt(index);
	}

	@JTranscSync
	public int codePointBefore(int index) {
		return toString().codePointBefore(index);
	}

	@JTranscSync
	public int codePointCount(int beginIndex, int endIndex) {
		return toString().codePointCount(beginIndex, endIndex);
	}

	@JTranscSync
	public int offsetByCodePoints(int index, int codePointOffset) {
		return toString().offsetByCodePoints(index, codePointOffset);
	}

	@JTranscSync
	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		String s = this.toString();
		int len = srcEnd - srcBegin;
		for (int n = 0; n < len; n++) dst[n] = s.charAt(srcBegin + n);
	}

	@JTranscSync
	public void setCharAt(int index, char ch) {
		replace(index, index + 1, String.valueOf(ch));
	}

	@JTranscSync
	public String substring(int start) {
		return this.substring(start, start + this.length());
	}

	@JTranscSync
	public String substring(int start, int end) {
		return this.toStringSync().substring(start, end);
	}

	@Override
	@JTranscSync
	public String toString() {
		return toStringSync();
	}
}
