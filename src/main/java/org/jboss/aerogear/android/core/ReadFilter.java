/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.android.core;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * This class wraps and builds the query parameters for filtering and pagination
 */
public class ReadFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String TAG = ReadFilter.class.getSimpleName();

    private Integer limit = Integer.MAX_VALUE;
    private Integer offset = 0;

    private JSONObject where = new JSONObject();
    private URI linkUri;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public JSONObject getWhere() {
        return where;
    }

    public void setWhere(JSONObject where) {
        this.where = where;
    }

    public URI getLinkUri() {
        return linkUri;
    }

    public void setLinkUri(URI linkUri) {
        this.linkUri = linkUri;
    }

    /**
     * @return a URL encoded query which represents the values set in this object.
     */
    public String getQuery() {
        StringBuilder queryBuilder = new StringBuilder("?");
        String amp = "";

        if (limit != null && limit != Integer.MAX_VALUE) {
            queryBuilder.append(amp).append("limit=").append(limit);
            amp = "&";
        }

        if (offset != null && offset > 0) {
            queryBuilder.append(amp).append("offset=").append(offset);
            amp = "&";
        }

        if (where != null && where.length() > 0) {
            try {
                queryBuilder.append(amp).append(preparePathParam());
            } catch (UnsupportedEncodingException ex) {
                Log.e(TAG, "UTF-8 isn't supported on this platform", ex);
                throw new RuntimeException(ex);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            amp = "&";
        }

        return ("".equals(amp)) ? "" : queryBuilder.toString();
    }

    @SuppressWarnings( { "rawtypes" })
    private String preparePathParam() throws UnsupportedEncodingException, JSONException {

        StringBuilder sb = new StringBuilder();

        String amp = "";

        Iterator keys = where.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object value = where.get(key);
            sb.append(amp);
            sb.append(String.format("%s=%s",
                    URLEncoder.encode(key, "UTF-8"),
                    URLEncoder.encode(value.toString(), "UTF-8")
                    ));
            amp = "&";
        }

        return sb.toString();

    }

}
