package link.yangxin.rpc.transport.impl;

import link.yangxin.rpc.transport.RequestHandler;
import link.yangxin.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yangxin
 * @date 2020/2/15
 */
@Slf4j
public class HttpTransportServer implements TransportServer {

    private RequestHandler requestHandler;

    private Server server;

    @Override
    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);

        // servlet接受请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            this.server.start();
            this.server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            this.server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("======= client connect ========");
            InputStream inputStream = req.getInputStream();

            OutputStream outputStream = resp.getOutputStream();

            if (requestHandler != null) {
                requestHandler.onRequest(inputStream, outputStream);
            }
        }
    }

}