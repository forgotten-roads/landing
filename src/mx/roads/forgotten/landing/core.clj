(ns mx.roads.forgotten.landing.core
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn foo
  "I don't do a whole lot."
  []
  :hello-world)

(defroutes routes
  (GET "/" [:as req]
    "This is the landing page")
  (route/resources "/"))
