package com.mypongserver.page;

import java.io.PrintWriter;
import java.text.MessageFormat;


public class PageGenerator {

    public static void writePage(String userId, PrintWriter writer, boolean newUser) {
        writer.print(getReloadPageScript());
        writer.print(MessageFormat.format("<h1>Hello {0} ur id is: {1}</h1>", newUser ? "new user" : "user", userId));
        writer.print("\n<form name=\"sendIdForm\" method=\"post\"> ");
        writer.print(MessageFormat.format("\n\t <input type=\"hidden\" name=\"id\" value = {0} /> ",
                userId != null && !userId.isEmpty() ? userId : ""));
        writer.print("\n\r</form>");
    }

    protected static String getReloadPageScript() {
        StringBuilder sb = new StringBuilder();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append("\twindow.onload = setInterval(function() {document.forms[\"sendIdForm\"].submit()}, 1000); \n");
        sb.append("\r</script>");
        return sb.toString();
    }

}
