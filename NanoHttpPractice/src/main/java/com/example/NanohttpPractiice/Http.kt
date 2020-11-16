package com.example.NanohttpPractiice

import fi.iki.elonen.NanoHTTPD

class Http(port: Int): NanoHTTPD(port) {

    override fun serve(session: IHTTPSession?): Response {
        return super.serve(session)
    }
}