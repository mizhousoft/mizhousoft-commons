/*
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mizhousoft.commons.okhttp.digest.fromhttpclient;

public final class HTTP {
    public static final int CR = 13;
    public static final int LF = 10;
    public static final int SP = 32;
    public static final int HT = 9;
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String CONTENT_LEN = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String EXPECT_DIRECTIVE = "Expect";
    public static final String CONN_DIRECTIVE = "Connection";
    public static final String TARGET_HOST = "Host";
    public static final String USER_AGENT = "User-Agent";
    public static final String DATE_HEADER = "Date";
    public static final String SERVER_HEADER = "Server";
    public static final String EXPECT_CONTINUE = "100-Continue";
    public static final String CONN_CLOSE = "Close";
    public static final String CONN_KEEP_ALIVE = "Keep-Alive";
    public static final String CHUNK_CODING = "chunked";
    public static final String IDENTITY_CODING = "identity";
    public static final String UTF_8 = "UTF-8";
    public static final String UTF_16 = "UTF-16";
    public static final String US_ASCII = "US-ASCII";
    public static final String ASCII = "ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_PROTOCOL_CHARSET = "US-ASCII";
    public static final String OCTET_STREAM_TYPE = "application/octet-stream";
    public static final String PLAIN_TEXT_TYPE = "text/plain";
    public static final String CHARSET_PARAM = "; charset=";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

    public static boolean isWhitespace(char ch) {
        return ch == 32 || ch == 9 || ch == 13 || ch == 10;
    }

    private HTTP() {
    }
}
