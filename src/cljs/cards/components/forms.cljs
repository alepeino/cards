(ns cards.components.forms
  (:require [cards.utils :refer [deep-merge]]))

(defmulti input #(:field %2))

(defmethod input :textarea [id attrs]
  [:textarea (merge attrs {:id id})])

(defmethod input :list [id attrs]
  [:select
   (-> attrs (assoc :id id) (dissoc :options))
   (for [[key text] (:options attrs)]
     [:option {:key key} text])])

(defmethod input :default [id attrs]
  [:input (merge attrs {:id id})])

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
