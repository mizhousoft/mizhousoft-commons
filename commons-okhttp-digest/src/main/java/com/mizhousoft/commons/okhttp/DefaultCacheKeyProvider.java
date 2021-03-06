package com.mizhousoft.commons.okhttp;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * The default version of the cache key provider, which simply takes the request URL / port for
 */
public final class DefaultCacheKeyProvider implements CacheKeyProvider {
    /**
     * Provides the caching key for the given request. Can be used to share passwords accross multiple subdomains.
     *
     * @param request the http request.
     * @return the cache key.
     */
    @Override
    public String getCachingKey(Request request) {
        final HttpUrl url = request.url();
        if (url == null)
            return null;
        return url.scheme() + ":" + url.host() + ":" + url.port();
    }
}
