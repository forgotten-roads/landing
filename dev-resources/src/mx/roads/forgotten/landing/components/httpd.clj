(ns mx.roads.forgotten.landing.components.httpd
  "FRMX landing page HTTP system component.

  For more information, see the module-level code comments in
  `mx.roads.forgotten.landing.components.httpd`."
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [nginx.clojure.embed :as embed]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn inject-app
  "Make app components available to request handlers.

  Note that even though it is called `:component`, the association performed
  in this function is for the `:httpd` component as defined in
  `mx.roads.forgotten.app-server.components`. This is done for the sake of
  clarity in other parts of the codebase where `:httpd` might be ambigious and
  `:component` is a better symmantic fit."
  [handler component]
  (fn [request]
    (handler (assoc request :component component))))

(defrecord HTTPServer [ring-handler]
  component/Lifecycle

  (start [component]
    (log/info "Starting FRMX landing page HTTP server ...")
    (let [http-cfg (get-in component [:cfg :httpd])
          handler (inject-app ring-handler component)
          server (embed/run-server handler http-cfg)]
      (log/debug "Using config:" http-cfg)
      (log/debug "Component keys:" (keys component))
      (log/debug "Successfully created server:" server)
      (assoc component :httpd server)))

  (stop [component]
    (log/info "* Stopping FRMX landing page HTTP server ...")
    (log/debug "Component keys" (keys component))
    (if-let [server (:httpd component)]
      (do (log/debug "Using server object:" server)
          (embed/stop-server)))
    (assoc component :httpd nil)))

(defn new-server [ring-handler]
  (->HTTPServer ring-handler))
