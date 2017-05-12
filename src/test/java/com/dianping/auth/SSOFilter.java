package com.dianping.auth;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Created by georgeliu on 2017/4/18.
 */
public class SSOFilter implements Filter {

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        requestSpec.header("Cookie", "ssoid=516ef802dd*f46c79a575800470d37d2; JSESSIONID=1xdffw4rtoa341x9lhg6jf4dy2");
        return ctx.next(requestSpec, responseSpec);
    }

}
