

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from TodayIDL.idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

#ifndef TodayIDLPlugin_1467360290_h
#define TodayIDLPlugin_1467360290_h

#include "TodayIDL.h"

struct RTICdrStream;

#ifndef pres_typePlugin_h
#include "pres/pres_typePlugin.h"
#endif

#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, start exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport __declspec(dllexport)
#endif

extern "C" {

    #define PostDataPlugin_get_sample PRESTypePluginDefaultEndpointData_getSample 
    #define PostDataPlugin_get_buffer PRESTypePluginDefaultEndpointData_getBuffer 
    #define PostDataPlugin_return_buffer PRESTypePluginDefaultEndpointData_returnBuffer 

    #define PostDataPlugin_create_sample PRESTypePluginDefaultEndpointData_createSample 
    #define PostDataPlugin_destroy_sample PRESTypePluginDefaultEndpointData_deleteSample 

    /* --------------------------------------------------------------------------------------
    Support functions:
    * -------------------------------------------------------------------------------------- */

    NDDSUSERDllExport extern PostData*
    PostDataPluginSupport_create_data_w_params(
        const struct DDS_TypeAllocationParams_t * alloc_params);

    NDDSUSERDllExport extern PostData*
    PostDataPluginSupport_create_data_ex(RTIBool allocate_pointers);

    NDDSUSERDllExport extern PostData*
    PostDataPluginSupport_create_data(void);

    NDDSUSERDllExport extern RTIBool 
    PostDataPluginSupport_copy_data(
        PostData *out,
        const PostData *in);

    NDDSUSERDllExport extern void 
    PostDataPluginSupport_destroy_data_w_params(
        PostData *sample,
        const struct DDS_TypeDeallocationParams_t * dealloc_params);

    NDDSUSERDllExport extern void 
    PostDataPluginSupport_destroy_data_ex(
        PostData *sample,RTIBool deallocate_pointers);

    NDDSUSERDllExport extern void 
    PostDataPluginSupport_destroy_data(
        PostData *sample);

    NDDSUSERDllExport extern void 
    PostDataPluginSupport_print_data(
        const PostData *sample,
        const char *desc,
        unsigned int indent);

    /* ----------------------------------------------------------------------------
    Callback functions:
    * ---------------------------------------------------------------------------- */

    NDDSUSERDllExport extern PRESTypePluginParticipantData 
    PostDataPlugin_on_participant_attached(
        void *registration_data, 
        const struct PRESTypePluginParticipantInfo *participant_info,
        RTIBool top_level_registration, 
        void *container_plugin_context,
        RTICdrTypeCode *typeCode);

    NDDSUSERDllExport extern void 
    PostDataPlugin_on_participant_detached(
        PRESTypePluginParticipantData participant_data);

    NDDSUSERDllExport extern PRESTypePluginEndpointData 
    PostDataPlugin_on_endpoint_attached(
        PRESTypePluginParticipantData participant_data,
        const struct PRESTypePluginEndpointInfo *endpoint_info,
        RTIBool top_level_registration, 
        void *container_plugin_context);

    NDDSUSERDllExport extern void 
    PostDataPlugin_on_endpoint_detached(
        PRESTypePluginEndpointData endpoint_data);

    NDDSUSERDllExport extern void    
    PostDataPlugin_return_sample(
        PRESTypePluginEndpointData endpoint_data,
        PostData *sample,
        void *handle);    

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_copy_sample(
        PRESTypePluginEndpointData endpoint_data,
        PostData *out,
        const PostData *in);

    /* ----------------------------------------------------------------------------
    (De)Serialize functions:
    * ------------------------------------------------------------------------- */

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_serialize(
        PRESTypePluginEndpointData endpoint_data,
        const PostData *sample,
        struct RTICdrStream *stream, 
        RTIBool serialize_encapsulation,
        RTIEncapsulationId encapsulation_id,
        RTIBool serialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_deserialize_sample(
        PRESTypePluginEndpointData endpoint_data,
        PostData *sample, 
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    PostDataPlugin_serialize_to_cdr_buffer(
        char * buffer,
        unsigned int * length,
        const PostData *sample); 

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_deserialize(
        PRESTypePluginEndpointData endpoint_data,
        PostData **sample, 
        RTIBool * drop_sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    PostDataPlugin_deserialize_from_cdr_buffer(
        PostData *sample,
        const char * buffer,
        unsigned int length);    

    NDDSUSERDllExport extern RTIBool
    PostDataPlugin_skip(
        PRESTypePluginEndpointData endpoint_data,
        struct RTICdrStream *stream, 
        RTIBool skip_encapsulation,  
        RTIBool skip_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern unsigned int 
    PostDataPlugin_get_serialized_sample_max_size_ex(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool * overflow,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);    

    NDDSUSERDllExport extern unsigned int 
    PostDataPlugin_get_serialized_sample_max_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int 
    PostDataPlugin_get_serialized_sample_min_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int
    PostDataPlugin_get_serialized_sample_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment,
        const PostData * sample);

    /* --------------------------------------------------------------------------------------
    Key Management functions:
    * -------------------------------------------------------------------------------------- */
    NDDSUSERDllExport extern PRESTypePluginKeyKind 
    PostDataPlugin_get_key_kind(void);

    NDDSUSERDllExport extern unsigned int 
    PostDataPlugin_get_serialized_key_max_size_ex(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool * overflow,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int 
    PostDataPlugin_get_serialized_key_max_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_serialize_key(
        PRESTypePluginEndpointData endpoint_data,
        const PostData *sample,
        struct RTICdrStream *stream,
        RTIBool serialize_encapsulation,
        RTIEncapsulationId encapsulation_id,
        RTIBool serialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_deserialize_key_sample(
        PRESTypePluginEndpointData endpoint_data,
        PostData * sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    PostDataPlugin_deserialize_key(
        PRESTypePluginEndpointData endpoint_data,
        PostData ** sample,
        RTIBool * drop_sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    PostDataPlugin_serialized_sample_to_key(
        PRESTypePluginEndpointData endpoint_data,
        PostData *sample,
        struct RTICdrStream *stream, 
        RTIBool deserialize_encapsulation,  
        RTIBool deserialize_key, 
        void *endpoint_plugin_qos);

    /* Plugin Functions */
    NDDSUSERDllExport extern struct PRESTypePlugin*
    PostDataPlugin_new(void);

    NDDSUSERDllExport extern void
    PostDataPlugin_delete(struct PRESTypePlugin *);

    #define uiadd_retPlugin_get_sample PRESTypePluginDefaultEndpointData_getSample 
    #define uiadd_retPlugin_get_buffer PRESTypePluginDefaultEndpointData_getBuffer 
    #define uiadd_retPlugin_return_buffer PRESTypePluginDefaultEndpointData_returnBuffer 

    #define uiadd_retPlugin_create_sample PRESTypePluginDefaultEndpointData_createSample 
    #define uiadd_retPlugin_destroy_sample PRESTypePluginDefaultEndpointData_deleteSample 

    /* --------------------------------------------------------------------------------------
    Support functions:
    * -------------------------------------------------------------------------------------- */

    NDDSUSERDllExport extern uiadd_ret*
    uiadd_retPluginSupport_create_data_w_params(
        const struct DDS_TypeAllocationParams_t * alloc_params);

    NDDSUSERDllExport extern uiadd_ret*
    uiadd_retPluginSupport_create_data_ex(RTIBool allocate_pointers);

    NDDSUSERDllExport extern uiadd_ret*
    uiadd_retPluginSupport_create_data(void);

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPluginSupport_copy_data(
        uiadd_ret *out,
        const uiadd_ret *in);

    NDDSUSERDllExport extern void 
    uiadd_retPluginSupport_destroy_data_w_params(
        uiadd_ret *sample,
        const struct DDS_TypeDeallocationParams_t * dealloc_params);

    NDDSUSERDllExport extern void 
    uiadd_retPluginSupport_destroy_data_ex(
        uiadd_ret *sample,RTIBool deallocate_pointers);

    NDDSUSERDllExport extern void 
    uiadd_retPluginSupport_destroy_data(
        uiadd_ret *sample);

    NDDSUSERDllExport extern void 
    uiadd_retPluginSupport_print_data(
        const uiadd_ret *sample,
        const char *desc,
        unsigned int indent);

    /* ----------------------------------------------------------------------------
    Callback functions:
    * ---------------------------------------------------------------------------- */

    NDDSUSERDllExport extern PRESTypePluginParticipantData 
    uiadd_retPlugin_on_participant_attached(
        void *registration_data, 
        const struct PRESTypePluginParticipantInfo *participant_info,
        RTIBool top_level_registration, 
        void *container_plugin_context,
        RTICdrTypeCode *typeCode);

    NDDSUSERDllExport extern void 
    uiadd_retPlugin_on_participant_detached(
        PRESTypePluginParticipantData participant_data);

    NDDSUSERDllExport extern PRESTypePluginEndpointData 
    uiadd_retPlugin_on_endpoint_attached(
        PRESTypePluginParticipantData participant_data,
        const struct PRESTypePluginEndpointInfo *endpoint_info,
        RTIBool top_level_registration, 
        void *container_plugin_context);

    NDDSUSERDllExport extern void 
    uiadd_retPlugin_on_endpoint_detached(
        PRESTypePluginEndpointData endpoint_data);

    NDDSUSERDllExport extern void    
    uiadd_retPlugin_return_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret *sample,
        void *handle);    

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_copy_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret *out,
        const uiadd_ret *in);

    /* ----------------------------------------------------------------------------
    (De)Serialize functions:
    * ------------------------------------------------------------------------- */

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_serialize(
        PRESTypePluginEndpointData endpoint_data,
        const uiadd_ret *sample,
        struct RTICdrStream *stream, 
        RTIBool serialize_encapsulation,
        RTIEncapsulationId encapsulation_id,
        RTIBool serialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_deserialize_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret *sample, 
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    uiadd_retPlugin_serialize_to_cdr_buffer(
        char * buffer,
        unsigned int * length,
        const uiadd_ret *sample); 

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_deserialize(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret **sample, 
        RTIBool * drop_sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    uiadd_retPlugin_deserialize_from_cdr_buffer(
        uiadd_ret *sample,
        const char * buffer,
        unsigned int length);    

    NDDSUSERDllExport extern RTIBool
    uiadd_retPlugin_skip(
        PRESTypePluginEndpointData endpoint_data,
        struct RTICdrStream *stream, 
        RTIBool skip_encapsulation,  
        RTIBool skip_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern unsigned int 
    uiadd_retPlugin_get_serialized_sample_max_size_ex(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool * overflow,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);    

    NDDSUSERDllExport extern unsigned int 
    uiadd_retPlugin_get_serialized_sample_max_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int 
    uiadd_retPlugin_get_serialized_sample_min_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int
    uiadd_retPlugin_get_serialized_sample_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment,
        const uiadd_ret * sample);

    /* --------------------------------------------------------------------------------------
    Key Management functions:
    * -------------------------------------------------------------------------------------- */
    NDDSUSERDllExport extern PRESTypePluginKeyKind 
    uiadd_retPlugin_get_key_kind(void);

    NDDSUSERDllExport extern unsigned int 
    uiadd_retPlugin_get_serialized_key_max_size_ex(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool * overflow,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int 
    uiadd_retPlugin_get_serialized_key_max_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_serialize_key(
        PRESTypePluginEndpointData endpoint_data,
        const uiadd_ret *sample,
        struct RTICdrStream *stream,
        RTIBool serialize_encapsulation,
        RTIEncapsulationId encapsulation_id,
        RTIBool serialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_deserialize_key_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret * sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    uiadd_retPlugin_deserialize_key(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret ** sample,
        RTIBool * drop_sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    uiadd_retPlugin_serialized_sample_to_key(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_ret *sample,
        struct RTICdrStream *stream, 
        RTIBool deserialize_encapsulation,  
        RTIBool deserialize_key, 
        void *endpoint_plugin_qos);

    /* Plugin Functions */
    NDDSUSERDllExport extern struct PRESTypePlugin*
    uiadd_retPlugin_new(void);

    NDDSUSERDllExport extern void
    uiadd_retPlugin_delete(struct PRESTypePlugin *);

    #define uiadd_paramsPlugin_get_sample PRESTypePluginDefaultEndpointData_getSample 
    #define uiadd_paramsPlugin_get_buffer PRESTypePluginDefaultEndpointData_getBuffer 
    #define uiadd_paramsPlugin_return_buffer PRESTypePluginDefaultEndpointData_returnBuffer 

    #define uiadd_paramsPlugin_create_sample PRESTypePluginDefaultEndpointData_createSample 
    #define uiadd_paramsPlugin_destroy_sample PRESTypePluginDefaultEndpointData_deleteSample 

    /* --------------------------------------------------------------------------------------
    Support functions:
    * -------------------------------------------------------------------------------------- */

    NDDSUSERDllExport extern uiadd_params*
    uiadd_paramsPluginSupport_create_data_w_params(
        const struct DDS_TypeAllocationParams_t * alloc_params);

    NDDSUSERDllExport extern uiadd_params*
    uiadd_paramsPluginSupport_create_data_ex(RTIBool allocate_pointers);

    NDDSUSERDllExport extern uiadd_params*
    uiadd_paramsPluginSupport_create_data(void);

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPluginSupport_copy_data(
        uiadd_params *out,
        const uiadd_params *in);

    NDDSUSERDllExport extern void 
    uiadd_paramsPluginSupport_destroy_data_w_params(
        uiadd_params *sample,
        const struct DDS_TypeDeallocationParams_t * dealloc_params);

    NDDSUSERDllExport extern void 
    uiadd_paramsPluginSupport_destroy_data_ex(
        uiadd_params *sample,RTIBool deallocate_pointers);

    NDDSUSERDllExport extern void 
    uiadd_paramsPluginSupport_destroy_data(
        uiadd_params *sample);

    NDDSUSERDllExport extern void 
    uiadd_paramsPluginSupport_print_data(
        const uiadd_params *sample,
        const char *desc,
        unsigned int indent);

    /* ----------------------------------------------------------------------------
    Callback functions:
    * ---------------------------------------------------------------------------- */

    NDDSUSERDllExport extern PRESTypePluginParticipantData 
    uiadd_paramsPlugin_on_participant_attached(
        void *registration_data, 
        const struct PRESTypePluginParticipantInfo *participant_info,
        RTIBool top_level_registration, 
        void *container_plugin_context,
        RTICdrTypeCode *typeCode);

    NDDSUSERDllExport extern void 
    uiadd_paramsPlugin_on_participant_detached(
        PRESTypePluginParticipantData participant_data);

    NDDSUSERDllExport extern PRESTypePluginEndpointData 
    uiadd_paramsPlugin_on_endpoint_attached(
        PRESTypePluginParticipantData participant_data,
        const struct PRESTypePluginEndpointInfo *endpoint_info,
        RTIBool top_level_registration, 
        void *container_plugin_context);

    NDDSUSERDllExport extern void 
    uiadd_paramsPlugin_on_endpoint_detached(
        PRESTypePluginEndpointData endpoint_data);

    NDDSUSERDllExport extern void    
    uiadd_paramsPlugin_return_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params *sample,
        void *handle);    

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_copy_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params *out,
        const uiadd_params *in);

    /* ----------------------------------------------------------------------------
    (De)Serialize functions:
    * ------------------------------------------------------------------------- */

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_serialize(
        PRESTypePluginEndpointData endpoint_data,
        const uiadd_params *sample,
        struct RTICdrStream *stream, 
        RTIBool serialize_encapsulation,
        RTIEncapsulationId encapsulation_id,
        RTIBool serialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_deserialize_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params *sample, 
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    uiadd_paramsPlugin_serialize_to_cdr_buffer(
        char * buffer,
        unsigned int * length,
        const uiadd_params *sample); 

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_deserialize(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params **sample, 
        RTIBool * drop_sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    uiadd_paramsPlugin_deserialize_from_cdr_buffer(
        uiadd_params *sample,
        const char * buffer,
        unsigned int length);    

    NDDSUSERDllExport extern RTIBool
    uiadd_paramsPlugin_skip(
        PRESTypePluginEndpointData endpoint_data,
        struct RTICdrStream *stream, 
        RTIBool skip_encapsulation,  
        RTIBool skip_sample, 
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern unsigned int 
    uiadd_paramsPlugin_get_serialized_sample_max_size_ex(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool * overflow,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);    

    NDDSUSERDllExport extern unsigned int 
    uiadd_paramsPlugin_get_serialized_sample_max_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int 
    uiadd_paramsPlugin_get_serialized_sample_min_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int
    uiadd_paramsPlugin_get_serialized_sample_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment,
        const uiadd_params * sample);

    /* --------------------------------------------------------------------------------------
    Key Management functions:
    * -------------------------------------------------------------------------------------- */
    NDDSUSERDllExport extern PRESTypePluginKeyKind 
    uiadd_paramsPlugin_get_key_kind(void);

    NDDSUSERDllExport extern unsigned int 
    uiadd_paramsPlugin_get_serialized_key_max_size_ex(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool * overflow,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern unsigned int 
    uiadd_paramsPlugin_get_serialized_key_max_size(
        PRESTypePluginEndpointData endpoint_data,
        RTIBool include_encapsulation,
        RTIEncapsulationId encapsulation_id,
        unsigned int current_alignment);

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_serialize_key(
        PRESTypePluginEndpointData endpoint_data,
        const uiadd_params *sample,
        struct RTICdrStream *stream,
        RTIBool serialize_encapsulation,
        RTIEncapsulationId encapsulation_id,
        RTIBool serialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_deserialize_key_sample(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params * sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool 
    uiadd_paramsPlugin_deserialize_key(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params ** sample,
        RTIBool * drop_sample,
        struct RTICdrStream *stream,
        RTIBool deserialize_encapsulation,
        RTIBool deserialize_key,
        void *endpoint_plugin_qos);

    NDDSUSERDllExport extern RTIBool
    uiadd_paramsPlugin_serialized_sample_to_key(
        PRESTypePluginEndpointData endpoint_data,
        uiadd_params *sample,
        struct RTICdrStream *stream, 
        RTIBool deserialize_encapsulation,  
        RTIBool deserialize_key, 
        void *endpoint_plugin_qos);

    /* Plugin Functions */
    NDDSUSERDllExport extern struct PRESTypePlugin*
    uiadd_paramsPlugin_new(void);

    NDDSUSERDllExport extern void
    uiadd_paramsPlugin_delete(struct PRESTypePlugin *);

}

#if (defined(RTI_WIN32) || defined (RTI_WINCE)) && defined(NDDS_USER_DLL_EXPORT)
/* If the code is building on Windows, stop exporting symbols.
*/
#undef NDDSUSERDllExport
#define NDDSUSERDllExport
#endif

#endif /* TodayIDLPlugin_1467360290_h */

