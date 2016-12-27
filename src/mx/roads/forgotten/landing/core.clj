(ns mx.roads.forgotten.landing.core
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]))

(defn foo
  "I don't do a whole lot."
  []
  :hello-world)

(defroutes routes
  (GET "/XXX" [:as req]
    "This is the landing page")
  (route/resources "/"))

(def app
  (-> routes
      (wrap-defaults site-defaults)
      (wrap-resource "public")))
