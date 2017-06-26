(ns cards.components.forms
  (:require [cards.utils :refer [deep-merge]]))

(defn- fg-options [{{:keys [field]} :input :as overrides}]
  (deep-merge
    {:class "form-group"
     :field :container
     :label {:class "form-control-label"}
     :input {:class "form-control"
             :field :text}}
    (condp #(some %2 %1) #{field}
      [:checkbox :radio] {:class "form-check"
                          :label {:class "form-check-label"}
                          :input {:class "form-check-input"}}
      [:file] {:input {:class "form-control-file"}}
      {})
    overrides))

(defn- input [id attrs]
  [(case (:field attrs)
     :textarea :textarea
     :input)
   (merge attrs {:id id})])

(defn form-group
  ([id label]
   (form-group id label {}))
  ([id label opts]
   (if (keyword? opts)
     (form-group id label {:input {:field opts}})
     (let [opts (fg-options opts)]
      [:div (-> opts (dissoc :input) (dissoc :label))
       [:label (merge (:label opts) {:for id}) label]
       (input id (:input opts))]))))
