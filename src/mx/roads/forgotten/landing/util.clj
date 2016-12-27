(ns mx.roads.forgotten.landing.util
  (:require [mx.roads.forgotten.landing.const :as const])
  (:import [java.net URI]
           [java.net InetAddress]
           [jnr.posix POSIXFactory]))

(defn new-url
  ""
  [url-str]
  (new URI url-str))

(defn new-base-url
  ""
  [host port]
  (new-url (format "http://%s:%s/" host port)))

(defprotocol IURI
  (get-authority [this]
    "Returns the decoded authority component of this URI.")
  (get-fragment [this]
    "Returns the decoded fragment component of this URI.")
  (get-host [this]
    "Returns the host component of this URI.")
  (get-path [this]
    "Returns the decoded path component of this URI.")
  (get-port [this]
    "Returns the port number of this URI.")
  (get-query [this]
    "Returns the decoded query component of this URI.")
  (get-scheme [this]
    "Returns the scheme component of this URI.")
  (->str [this]
    "Returns the content of this URI as a string."))

(extend-protocol IURI URI
  (get-authority [this] (.getAuthority this))
  (get-fragment [this] (.getFragment this))
  (get-host [this] (.getHost this))
  (get-path [this] (.getPath this))
  (get-port [this] (.getPort this))
  (get-query [this] (.getQuery this))
  (get-scheme [this] (.getScheme this))
  (->str [this] (str this)))

(defn headless?
  ""
  []
  (System/getProperty "java.awt.headless"))

(defn get-desktop
  ""
  []
  (java.awt.Desktop/getDesktop))

(defn ui-web-browse
  ""
  [url]
  (.browse (get-desktop) url))

(defn get-default-ip
  ""
  []
  (.getHostAddress (InetAddress/getLocalHost)))

(defn add-shutdown-handler
  ""
  [func]
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. func)))

(defn join-thread
  ""
  []
  (.join (Thread/currentThread)))

(defn expand-user-home
  ""
  [^String filename]
  (.replaceFirst filename "^~" (System/getProperty "user.home")))

(defn in?
  "This function returns true if the provided seqenuce contains the given
  elment."
  [seq elm]
  (some #(= elm %) seq))

(defn set-env-config
  ""
  [filename]
  (let [posix (POSIXFactory/getNativePOSIX)]
    (.setenv posix const/env-config filename 1)))

(defn get-env-config
  ""
  []
  (let [posix (POSIXFactory/getNativePOSIX)]
    (.getenv posix const/env-config)))
