(ns clojure-simple-http.core
  (:require [org.httpkit.server :refer [run-server] :as http-server])
  (:gen-class))


(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello world!"})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)
    (println "Server stopped")))

(defn -main [& args]
  (reset! server (run-server #'app {:port 8080}))
  (println server " Server started on port 8080"))