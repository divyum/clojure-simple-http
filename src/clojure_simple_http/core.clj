(ns clojure-simple-http.core
  (:require [org.httpkit.server :refer [run-server]]
            [clj-time.core :as t]
            [compojure.core :refer :all]
            [compojure.route :as route]))

; returns current time.
(defn get-time
  []
  (let [response {:status  200
                  :headers {"Content-Type" "text/html"}
                  :body    (str (t/time-now))}]
    response))

; define routes.
(defroutes app
           (GET "/" [] "<h1>Welcome</h1>")
           (GET "/get-time" [] (get-time))
           (route/not-found "<h1>Page not found</h1>"))

; starting point of server.
(defn -main [& args]
  (run-server app {:port 8080})
  (println "Server started on port 8080"))