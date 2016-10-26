(ns mx.roads.forgotten.admin.core
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn foo
  "I don't do a whole lot."
  []
  :hello-world)

(defroutes routes
  (GET "/admin" [:as req]
    "This is the admin page")
  (route/resources "/admin"))
